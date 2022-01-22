package vk.com.merofunk.esscore.chat;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vk.com.merofunk.esscore.commands.PrefixCommand;
import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.EssCore;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Prefix 
{
	public static String getPrefix(CommandSender cs)
	{
		Configuration playerprops = new Configuration(EssCore.getInstance(), "players/" + cs.getName() + ".yml");
		if(PrefixCommand.isCustom((Player)cs))
			return playerprops.getString("Player." + ((Player) cs).getUniqueId() + ".Prefix");
		else
			return PermissionsEx.getUser((Player)cs).getPrefix();
	}
	
	public static String getPrefix(Player p)
	{
		if(PrefixCommand.isCustom(p))
			return EssCore.getInstance().getChat().getPlayerPrefix(p);
		else
			return PermissionsEx.getUser(p).getPrefix();
	}
	
	public static void setPrefix(CommandSender cs, String prefix) { EssCore.getInstance().getChat().setPlayerPrefix((Player)cs, prefix); }

	public static void setPrefix(Player p, String prefix) { EssCore.getInstance().getChat().setPlayerPrefix(p, prefix); }
}
