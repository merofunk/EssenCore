package vk.com.merofunk.esscore.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import vk.com.merofunk.esscore.chat.Prefix;
import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.EssCore;
import net.md_5.bungee.api.ChatColor;

public class Join implements Listener 
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		if(!EssCore.getInstance().SQLEnabled())
		{
			Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
			p.setPlayerListName(Prefix.getPrefix(p) + p.getName());
			playerprops.set("Player." + p.getUniqueId() + ".IGN", p.getName());
			playerprops.save();
			if(playerprops.getString("Player." + p.getUniqueId() + ".Prefix") != null)
			{
				Prefix.setPrefix(p, playerprops.getString("Player." + p.getUniqueId() + ".Prefix"));
				p.setPlayerListName(colorize(playerprops.getString("Player." + p.getUniqueId() + ".Prefix")) + p.getName());
			}
			if(playerprops.getString("Player." + p.getUniqueId() + ".DisplayName") != null)
			{
				p.setDisplayName(playerprops.getString("Player." + p.getUniqueId() + ".DisplayName"));
			}
			if(playerprops.getString("Player." + p.getUniqueId() + ".TabName") != null)
			{
				Prefix.setPrefix(p, playerprops.getString("Player." + p.getUniqueId() + ".Prefix"));
				p.setPlayerListName(colorize(playerprops.getString("Player." + p.getUniqueId() + ".Prefix") + playerprops.getString("Player." + p.getUniqueId() + ".TabName")));
			}
		}
		else
		{
			
		}
	}
	
	private String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
