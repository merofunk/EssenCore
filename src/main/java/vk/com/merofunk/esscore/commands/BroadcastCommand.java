package vk.com.merofunk.esscore.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;

public class BroadcastCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if(sender.hasPermission("foxxxess.broadcast"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
						.replace("%u", MessageConfiguration.getCommandUsage("BroadcastUsage")));
				return true;
			}
			else
			{
				String message = "";
				for(int i = 0; i < args.length; i++)
				{
					message = message + args[i] + " ";
				}
				
				if(message.length() <= EssCore.getInstance().getConfig().getInt("Broadcast.MinMessageLength"))
					Message.sendWithPrefix(sender, MessageConfiguration.getMessage("BroadcastMessageShorterThanExpected").replace("%i", String.valueOf(EssCore.getInstance().getConfig().getInt("Broadcast.MinMessageLength"))));
				else
					broadcast(sender, MessageConfiguration.getMessage("BroadcastPrefix") + message);
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		return false;
	}
	
	public static void broadcast(CommandSender cs, String message)
	{
		for(Player all : Bukkit.getOnlinePlayers())
		{
			if(cs.hasPermission("foxxxess.broadcast.colorized"))
			Message.send(all, colorize(message));
			else
				Message.send(all, message);
			
		}
	}
	
	private static String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
}
