package vk.com.merofunk.esscore.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.EssCore;

public class Message 
{
	public static void send(CommandSender cs, String message)
	{
		cs.sendMessage(colorize(message));
	}
	
	public static void send(Player p, String message)
	{
		p.sendMessage(colorize(message));
	}
	
	public static void sendWithPrefix(CommandSender cs, String message)
	{
		cs.sendMessage(colorize(getPrefix() + message));
	}
	
	public static void sendWithPrefix(Player p, String message)
	{
		p.sendMessage(colorize(getPrefix() + message));
	}
	
	private static String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	private static String getPrefix()
	{
		Configuration cfg = new Configuration(EssCore.getInstance(), "messages.yml");
		return cfg.getString("Messages.Prefix");
	}
}
