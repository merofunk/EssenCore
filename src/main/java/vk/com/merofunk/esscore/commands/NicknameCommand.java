package vk.com.merofunk.esscore.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;
import net.md_5.bungee.api.ChatColor;

public class NicknameCommand implements CommandExecutor
{

	private static boolean nicknamed;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.nick"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
						.replace("%u", MessageConfiguration.getCommandUsage("NickUsage")));
				return true;
			}
			else
			{
				if(sender.hasPermission("foxxxess.nick.set"))
				{
					Player p = (Player) sender;
					String nickname = args[0];
					switch(nickname)
					{
					case "reset":
						if(nicknamed)
						{
							p.setDisplayName(p.getName());
							nicknamed = false;
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NickResetSuccsessful"));
							Configuration playerprops = new Configuration(EssCore.getInstance(), "/players/" + p.getName() + ".yml");
							playerprops.set("Player." + p.getUniqueId() + ".DisplayName", null);
							playerprops.save();
							return false;
						}
						else
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotNicknamed"));
							return false;
						}
					default:
						int minLength = EssCore.getInstance().getConfig().getInt("NicknamesSettings.MinNicknameLength");
						int maxLength = EssCore.getInstance().getConfig().getInt("NicknamesSettings.MaxNicknameLength");

						if(nickname.length() <= minLength)
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NicknameShorterThanExpected").replace("%i", String.valueOf(minLength)));
							return true;
						}
						else if(!sender.hasPermission("foxxxess.nick.lengthbypass") || !sender.hasPermission("foxxxess.nick.*") && nickname.length() > maxLength)
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NicknameLongerThanExpected").replace("%i", String.valueOf(maxLength)));
							return true;
						}
						else
						{
							List<String> blacklistedNicknames = EssCore.getInstance().getConfig().getStringList("NicknamesSettings.BlacklistedNicknames");
							for(int i = 0; i < blacklistedNicknames.size(); i++)
							{
								if(nickname.contains(blacklistedNicknames.get(i)))
								{
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("BlacklistedNickname").replace("%n", nickname));
									return false;
								}
							}
							if(EssCore.getInstance().getConfig().getBoolean("NicknamesSettings.UseSymbol"))
							{
								
								if(sender.hasPermission("foxxxess.nick.usecolor"))
								{
									nicknamed = true;
									p.setDisplayName(colorize(EssCore.getInstance().getConfig().getString("NicknamesSettings.NicknameSymbol") + nickname));
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NickSetSuccsessful").replace("%n", nickname));
									Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
									playerprops.set("Player." + p.getUniqueId() + ".DisplayName", nickname);
									playerprops.save();
									return true;
								}
								else
								{
									nicknamed = true;
									p.setDisplayName(EssCore.getInstance().getConfig().getString("NicknamesSettings.NicknameSymbol") + nickname);
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NickSetSuccsessful").replace("%n", nickname));
									Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
									playerprops.set("Player." + p.getUniqueId() + ".DisplayName", nickname);
									playerprops.save();
									return true;
								}
								
							}
							else
							{
								if(sender.hasPermission("foxxxess.nick.usecolor"))
								{
									nicknamed = true;
									p.setDisplayName(colorize(nickname));
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NickSetSuccsessful").replace("%n", nickname));
									Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
									playerprops.set("Player." + p.getUniqueId() + ".DisplayName", nickname);
									playerprops.save();
									return true;
								}
								else
								{
									nicknamed = true;
									p.setDisplayName(nickname);
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NickSetSuccsessful").replace("%n", nickname));
									Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + p.getName() + ".yml");
									playerprops.set("Player." + p.getUniqueId() + ".DisplayName", nickname);
									playerprops.save();
									return true;
								}
							}
							
						}
							
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
