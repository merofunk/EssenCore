package vk.com.merofunk.esscore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;

public class ConfigReload implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.configurations.reload"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
						.replace("%u", MessageConfiguration.getCommandUsage("ConfigReloadUsage")));
				return false;
			}
			else
			{
				switch(args[0])
				{
				case "default":
					EssCore.getInstance().reloadConfig();
					EssCore.getInstance().saveDefaultConfig();
					Message.sendWithPrefix(sender, MessageConfiguration.getMessage("ConfigReloaded").replace("%c", args[0]));
					break;
				case "messages":
					Configuration msg = new Configuration(EssCore.getInstance(), "messages.yml");
					msg.reload();
					Message.sendWithPrefix(sender, MessageConfiguration.getMessage("ConfigReloaded").replace("%c", args[0]));
					break;
				default:
					Message.sendWithPrefix(sender, MessageConfiguration.getMessage("ConfigNotFound").replace("%c", args[0]));
					break;
				}
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		return false;
	}

}
