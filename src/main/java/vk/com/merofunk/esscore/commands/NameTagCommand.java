package vk.com.merofunk.esscore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;
import vk.com.merofunk.esscore.utilities.PrefixAboveHead;
import vk.com.merofunk.esscore.utilities.TeamAction;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class NameTagCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.nametag"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments").replace("%u", MessageConfiguration.getCommandUsage("NameTagUsage")));
				return true;
			}
			else
			{
				switch(args[0])
				{
				case "set":
					String prefix = "";
					
					for(int i = 1; i < args.length; i++)
					{
						prefix = prefix + args[i] + " ";
					}
					
					int minLength = EssCore.getInstance().getConfig().getInt("NameTagSettings.MinNameTagLength");
					if(prefix.contains("&k"))
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("DontUseMagicColor"));
						return false;
					}
					if(prefix.length() > 16)
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NameTagLongerThanExpected").replace("%i", String.valueOf(16)));
						return false;
					}
					else if(prefix.length() <= minLength)
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NameTagShorterThanExpected").replace("%i", String.valueOf(minLength)));
						return false;
					}
					else
					{
						PrefixAboveHead.set((Player)sender, colorize(prefix), TeamAction.UPDATE);
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NameTagSetted").replace("%n", prefix).replace("%p", sender.getName()));
						if(!EssCore.getInstance().SQLEnabled())
						{
							Configuration conf = new Configuration(EssCore.getInstance(), "players/" + sender.getName() + ".yml");
							conf.set("Player." + ((Player) sender).getUniqueId() + ".NameTag", prefix);
							conf.save();
						}
						else
						{
							
						}
						
					}
					break;
				case "reset":
					if(isNameTagSetted((Player)sender))
					{
						PrefixAboveHead.set((Player)sender, colorize(PermissionsEx.getUser((Player)sender).getPrefix()), TeamAction.UPDATE);
						if(!EssCore.getInstance().SQLEnabled())
						{
							Configuration conf = new Configuration(EssCore.getInstance(), "players/" + sender.getName() + ".yml");
							conf.set("Player." + ((Player) sender).getUniqueId() + ".NameTag", null);
							conf.save();
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NameTagReset"));
						}
						else
						{
							
						}	
					}
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
	
	private String colorize(String s)
	{
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static boolean isNameTagSetted(Player p)
	{
		Configuration conf = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
		return conf.getString("Player." + p.getUniqueId() + ".NameTag") != null;
	}

}
