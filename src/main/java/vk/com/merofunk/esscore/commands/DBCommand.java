package vk.com.merofunk.esscore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.messages.Message;

public class DBCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.database"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArgumets").replace("%u", MessageConfiguration.getCommandUsage("")));
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		return false;
	}
	
}
