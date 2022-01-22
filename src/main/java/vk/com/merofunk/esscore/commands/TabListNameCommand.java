package vk.com.merofunk.esscore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.chat.Prefix;
import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;

public class TabListNameCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.tabnick"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
						.replace("%u", MessageConfiguration.getCommandUsage("TabListUsage")));
				return true;
			}
			else
			{
				switch(args[0])
				{
				case "set":
					if(sender.hasPermission("foxxxess.tabnick.set"))
					{
						if(args.length == 1)
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
									.replace("%u", MessageConfiguration.getCommandUsage("TabListUsage")));
							return true;
						}
						String name = args[1];
						if(name.contains("&k"))
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("DontUseMagicColor"));
							return false;
						}
						else
						{
							if(name.contains(sender.getName()))
							{
								Player p = (Player) sender;
								p.setPlayerListName(Prefix.getPrefix(p) + colorize(name));
								Message.sendWithPrefix(sender, MessageConfiguration.getMessage("TabNameChangedSuccsessfully").replace("%s", Prefix.getPrefix(p) + name));
								Configuration conf = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
								conf.set("Player." + p.getUniqueId() + ".TabName", name);
								conf.save();
								
								return false;
							}
							else
							{
								Message.sendWithPrefix(sender, MessageConfiguration.getMessage("YourNickOnly"));
								return false;
							}
						}
					}
					else
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("TabNameSetNoPermission"));
					}
					break;
				case "reset":
					if(sender.hasPermission("foxxxess.tabnick.reset"))
					{
						Player p = (Player) sender;
						p.setPlayerListName(Prefix.getPrefix(p) + p.getName());
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("TabNameReset"));
						Configuration conf = new Configuration(EssCore.getInstance(), "players/" + p.getName());
						conf.set("Player." + p.getUniqueId() + ".TabName", null);
						conf.save();
						return false;
					}
					else
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("TabNameResetNoPermission"));
						return false;
					}
				}
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		return false;
	}

	private String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
}
