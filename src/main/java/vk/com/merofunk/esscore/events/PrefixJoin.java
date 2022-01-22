package vk.com.merofunk.esscore.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import vk.com.merofunk.esscore.commands.NameTagCommand;
import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.utilities.PrefixAboveHead;
import vk.com.merofunk.esscore.utilities.TeamAction;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PrefixJoin implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event)
	{
		Configuration cfg = new Configuration(EssCore.getInstance(), "players/" + event.getPlayer().getName());
		if(NameTagCommand.isNameTagSetted(event.getPlayer()))
		{
			PrefixAboveHead.set(event.getPlayer(), colorize(cfg.getString("Player." + event.getPlayer().getUniqueId() + ".NameTag")), TeamAction.CREATE);
		}
		else
		{
			PrefixAboveHead.set(event.getPlayer(), colorize(PermissionsEx.getUser(event.getPlayer()).getPrefix()), TeamAction.CREATE);
		}
		
	}
	
	private String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
