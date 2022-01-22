package vk.com.merofunk.esscore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;

public class GetStringFromConf implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.configtest"))
		{
			Configuration conf = new Configuration(EssCore.getInstance(), "messages.yml");
			String s = conf.getString("Messages.Prefix");
			Message.send(sender, s);
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		
		
		return false;
	}

}
