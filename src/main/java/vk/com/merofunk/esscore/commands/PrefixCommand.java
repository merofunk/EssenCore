package vk.com.merofunk.esscore.commands;

import java.util.List;

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

public class PrefixCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.prefix"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments").replace("%u",
						MessageConfiguration.getCommandUsage("PrefixUsage")));
				return true;
			}
			else
			{
				switch(args[0])
				{
				case "set":
					if(sender.hasPermission("foxxxess.prefix.set") || sender.hasPermission("foxxxess.prefix.*"))
					{
						String prefix = "";
						for(int i = 1; i < args.length; i++)
						{
							prefix = prefix + args[i] + " ";
						}
						int maxPrefixLength = EssCore.getInstance().getConfig().getInt("PrefixSettings.MaxPrefixLength");
						List<String> blacklistedPrefixes = EssCore.getInstance().getConfig().getStringList("PrefixSettings.BlacklistedPrefixes");
						if(!sender.hasPermission("foxxxess.prefix.lengthbypass") || !sender.hasPermission("foxxxess.prefix.*") && prefix.length() > maxPrefixLength)
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PrefixLongerThanExpected").replace("%i", String.valueOf(maxPrefixLength)));
							return true;
						}
						int minPrefixLength = EssCore.getInstance().getConfig().getInt("PrefixSettings.MinPrefixLength");
						if(prefix.length() <= minPrefixLength)
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PrefixShorterThanExpected").replace("%i", String.valueOf(minPrefixLength)));
							return true;
						}
						for(int i = 0; i < blacklistedPrefixes.size(); i++)
						{
							if(prefix.contains(blacklistedPrefixes.get(i)))
							{
								Message.sendWithPrefix(sender, MessageConfiguration.getMessage("BlacklistedPrefix").replace("%p", prefix));
								return false;
							}
						}
						Prefix.setPrefix(sender, colorize(prefix));
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PrefixSetSuccsess").replace("%p", prefix));
						Player p = (Player) sender;
						p.setPlayerListName(colorize(prefix + p.getName()));
						Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
						playerprops.set("Player." + p.getUniqueId() + ".Prefix", prefix);
						playerprops.save();
						
					}
					else Message.sendWithPrefix(sender, MessageConfiguration.getMessage("SetPrefixNoPermission"));
					break;
				case "remove":
					if(sender.hasPermission("foxxxess.prefix.remove") || sender.hasPermission("foxxxess.prefix.*"))
					{
						Prefix.setPrefix(sender, colorize(Prefix.getPrefix(sender)));
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("SuccsessfulPrefixRemoval"));
						Player p = (Player) sender;
						Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
						playerprops.set("Player." + p.getUniqueId() + ".Prefix", null);
						playerprops.save();
						p.setPlayerListName(Prefix.getPrefix(p) + p.getName());
					}
					else Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PrefixRemovalNoPermission"));
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
	
	public static boolean isCustom(Player p)
	{
		Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
		return playerprops.getString("Player." + p.getUniqueId() + ".Prefix") != null;
	}
	

}
