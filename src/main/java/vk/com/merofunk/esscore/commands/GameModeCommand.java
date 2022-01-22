package vk.com.merofunk.esscore.commands;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.configurations.MessageConfiguration;
import vk.com.merofunk.esscore.messages.Message;

public class GameModeCommand implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) 
	{
		final String SURVIVAL = MessageConfiguration.getMessage("Survival");
		final String CREATIVE = MessageConfiguration.getMessage("Creative");
		final String ADVENTURE = MessageConfiguration.getMessage("Adventure");
		final String SPECTATOR = MessageConfiguration.getMessage("Spectator");
		final String UPDATED_OTHER = MessageConfiguration.getMessage("GameModeUpdatedOther");
		final String UPDATED = MessageConfiguration.getMessage("GameModeUpdated");
		final String NO_PERM_GAMEMODE_OTHER = MessageConfiguration.getMessage("GameModeUpdateNoPermissionOther");
		final String NO_PERM_GAMEMODE = MessageConfiguration.getMessage("GameModeNoPermission");
		final String NO_PERM_OTHER = MessageConfiguration.getMessage("GameModeNoPermissionOther");
		
		if(sender.hasPermission("foxxxess.gamemode"))
		{
			if(args.length == 0)
			{
				Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NotEnoughArguments").replace("%u", MessageConfiguration
						.getCommandUsage("GameModeUsage")));
				Message.send(sender, MessageConfiguration.getMessage("RedStarMeaning"));
				Message.send(sender, MessageConfiguration.getMessage("YellowStarMeaning"));
				return false;
			}
			if(args.length == 1)
			{
				
				String modestr = args[0];
				if(!StringUtils.isNumeric(modestr))
				{
					switch(modestr)
					{
					case "survival":
						if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SURVIVAL);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SURVIVAL));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SURVIVAL));
							break;
						}
					case "s":
						if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SURVIVAL);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SURVIVAL));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SURVIVAL));
							break;
						}
					case "creative":
						if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.CREATIVE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", CREATIVE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", CREATIVE));
							break;
						}
					case "c":
						if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.CREATIVE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", CREATIVE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", CREATIVE));
							break;
						}
					case "adventure":
						if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.ADVENTURE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", ADVENTURE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", ADVENTURE));
							break;
						}
					case "a":
						if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.ADVENTURE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", ADVENTURE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", ADVENTURE));
							break;
						}
					case "spectator":
						if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SPECTATOR);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SPECTATOR));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SPECTATOR));
							break;
						}
					case "sp":
						if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SPECTATOR);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SPECTATOR));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SPECTATOR));
							break;
						}
					}
				}
				else
				{
					int modeint = Integer.parseInt(args[0]);
					switch(modeint)
					{
					case 0:
						if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SURVIVAL);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SURVIVAL));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SURVIVAL));
							break;
						}
					case 1:
						if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.CREATIVE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", CREATIVE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", CREATIVE));
							break;
						}
					case 2:
						if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.ADVENTURE);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", ADVENTURE));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", ADVENTURE));
							break;
						}
					case 3:
						if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
						{
							setGameMode(sender, GameMode.SPECTATOR);
							Message.sendWithPrefix(sender, UPDATED.replace("%gm", SPECTATOR));
							break;
						}
						else
						{
							Message.sendWithPrefix(sender, NO_PERM_GAMEMODE.replace("%gm", SPECTATOR));
							break;
						}
					}
				}
				
			}
			else if(args.length == 2)
			{
				if(sender.hasPermission("foxxxess.gamemode.other") || sender.hasPermission("foxxxess.gamemode.*"))
				{
					Player p = Bukkit.getPlayer(args[1]);
					if(p == null || !p.isOnline())
					{
						Message.sendWithPrefix(sender, MessageConfiguration.getMessage("PlayerNotFound").replace("%p", args[1]));
						return false;
					}
					String modestr = args[0];
					if(!StringUtils.isNumeric(modestr))
					{
						switch(modestr)
						{
						case "survival":
							if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SURVIVAL);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
						case "s":
							if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SURVIVAL);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
						case "creative":
							if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.CREATIVE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
						case "c":
							if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.CREATIVE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
						case "adventure":
							if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.ADVENTURE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
						case "a":
							if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.ADVENTURE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
						case "spectator":
							if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SPECTATOR);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
						case "sp":
							if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SPECTATOR);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
							
						}
					}
					else
					{
						int modeint = Integer.parseInt(args[0]);
						switch(modeint)
						{
						case 0:
							if(sender.hasPermission("foxxxess.gamemode.survival") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SURVIVAL);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SURVIVAL).replace("%p", args[1]));
								break;
							}
						case 1:
							if(sender.hasPermission("foxxxess.gamemode.creative") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.CREATIVE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", CREATIVE).replace("%p", args[1]));
								break;
							}
						case 2:
							if(sender.hasPermission("foxxxess.gamemode.adventure") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.ADVENTURE);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", ADVENTURE).replace("%p", args[1]));
								break;
							}
						case 3:
							if(sender.hasPermission("foxxxess.gamemode.spectator") || sender.hasPermission("foxxxess.gamemode.*"))
							{
								setGameMode(p, GameMode.SPECTATOR);
								Message.sendWithPrefix(sender, UPDATED_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
							else
							{
								Message.sendWithPrefix(sender, NO_PERM_GAMEMODE_OTHER.replace("%gm", SPECTATOR).replace("%p", args[1]));
								break;
							}
						}
					}
					
				}
				else
				{
					Message.sendWithPrefix(sender, NO_PERM_OTHER.replace("%p", args[1]));
					return false;
				}
			}
		}
		else
		{
			Message.sendWithPrefix(sender, MessageConfiguration.getMessage("NoPermission"));
		}
		return false;
	}
	
	private void setGameMode(CommandSender cs, GameMode gm)
	{
		Player p = (Player) cs;
		p.setGameMode(gm);
	}

}
