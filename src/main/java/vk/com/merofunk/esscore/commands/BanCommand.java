package vk.com.merofunk.esscore.commands;

import java.util.List;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.EssCore;
import vk.com.merofunk.esscore.messages.Message;

public class BanCommand implements CommandExecutor
{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		if(sender.hasPermission("foxxxess.ban"))
		{
			if(args.length == 0 || args.length == 1)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments")
						.replace("%u", MessageConfiguration.getCommandUsage("BanUsage")));
				return false;
			}
			else
			{
				Player p = Bukkit.getPlayer(args[0]);
				if(p == null) 
				{
					if(!sender.hasPermission("foxxxess.ban.offline"))
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PlayerNotFound").replace("%p", args[0]));
						return false;
					}
					else
					{
						OfflinePlayer off = Bukkit.getOfflinePlayer(args[0]);
						List<String> exceptions = EssCore.getInstance().getConfig().getStringList("BanSettings.Exceptions");
						
						if(exceptions.contains(args[0]))
						{
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("CantBanThisPerson").replace("%p", args[0]));
							return false;
						}
						else
						{
							String reason = "";
							for(int i = 1; i < args.length; i++)
							{
								reason = reason + args[i] + " ";
							}
							if(reason.length() <= EssCore.getInstance().getConfig().getInt("BanSettings.MinReasonLength"))
							{
								sender.sendMessage(reason);
								Message.sendWithPrefix(sender, MessageConfiguration.getMessage("ReasonShorterThanExpected").replace("%i", 
										String.valueOf(EssCore.getInstance().getConfig().getInt("BanSettings.MinReasonLength"))));
								return false;
							}
							else
							{
								Bukkit.getBanList(Type.NAME).addBan(off.getName(), reason, null, sender.getName());
								if(EssCore.getInstance().SQLEnabled())
								{
									//TODO: SQL Actions
								}
								else
								{
									Configuration banConf = new Configuration(EssCore.getInstance(), "banned-players/" + args[0] + ".yml");
									banConf.set("BanInfo." + off.getUniqueId() + ".IGN", args[0]);
									banConf.set("BanInfo." + off.getUniqueId() + ".WhoBanned", sender.getName());
									banConf.set("BanInfo." + off.getUniqueId() + ".Reason", reason);
									banConf.set("BanInfo." + off.getUniqueId() + ".TimeToUnban", "never");
									banConf.save();
									Message.sendWithPrefix(sender, MessageConfiguration.getMessage("SucsessfulBan").replace("%p", args[0]).replace("%r", reason));
									BroadcastCommand.broadcast(sender, MessageConfiguration.getMessage("Prefix") + MessageConfiguration.getMessage("BanBroadcast").replace("%p", args[0]).replace("%r", reason).replace("%a", sender.getName()));
									reason = "";
								}
								
							}
						}
					}
				}
				else
				{
					List<String> exceptions = EssCore.getInstance().getConfig().getStringList("BanSettings.Exceptions");
					
					if(exceptions.contains(args[0]))
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("CantBanThisPerson").replace("%p", args[0]));
						return false;
					}
					else
					{
						String reason = "";
						for(int i = 1; i < args.length; i++)
						{
							reason = reason + args[i] + " ";
						}
						if(reason.length() <= EssCore.getInstance().getConfig().getInt("BanSettings.MinReasonLength"))
						{
							sender.sendMessage(reason);
							Message.sendWithPrefix(sender, MessageConfiguration.getMessage("ReasonShorterThanExpected").replace("%i", 
									String.valueOf(EssCore.getInstance().getConfig().getInt("BanSettings.MinReasonLength"))));
							return false;
						}
						else
						{
							Bukkit.getBanList(Type.NAME).addBan(p.getName(), reason, null, sender.getName());
							if(EssCore.getInstance().SQLEnabled())
							{
								//TODO: SQL Actions
							}
							else
							{
								Configuration banConf = new Configuration(EssCore.getInstance(), "banned-players/" + args[0] + ".yml");
								banConf.set("BanInfo." + p.getUniqueId() + ".IGN", args[0]);
								banConf.set("BanInfo." + p.getUniqueId() + ".WhoBanned", sender.getName());
								banConf.set("BanInfo." + p.getUniqueId() + ".Reason", reason);
								banConf.set("BanInfo." + p.getUniqueId() + ".TimeToUnban", "never");
								banConf.save();
								Message.sendWithPrefix(sender, MessageConfiguration.getMessage("SucsessfulBan").replace("%p", args[0]).replace("%r", reason));
								BroadcastCommand.broadcast(sender, MessageConfiguration.getMessage("BanBroadcast").replace("%p", args[0]).replace("%r", reason));
								reason = "";
							}
						}
					}
				}
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
			return false;
		}
		return false;
	}
}