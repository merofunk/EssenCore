package vk.com.merofunk.esscore.chat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import vk.com.merofunk.esscore.EssCore;

public class Format implements Listener
{
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event)
	{
		Player p = event.getPlayer();
		String format = EssCore.getInstance().getConfig().getString("Global.ChatFormat");
		event.setFormat(colorize(format.replace("%prefix", Prefix.getPrefix(p)).replace("%n", p.getDisplayName()).replace("%m", event.getMessage())));	
	}
	
	private String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
