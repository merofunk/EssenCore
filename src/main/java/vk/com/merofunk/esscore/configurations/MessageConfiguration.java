package vk.com.merofunk.esscore.configurations;

import vk.com.merofunk.esscore.EssCore;

public class MessageConfiguration 
{
	public static String getMessage(String type)
	{
		Configuration cfg = new Configuration(EssCore.getInstance(), "messages.yml");
		if(cfg.getString("Messages." + type) != null) return cfg.getString("Messages." + type);
		else return cfg.getString("Messages.ConfigurationMessageWasntFound").replace("%msg", type);
	}
	
	public static String getCommandUsage(String command)
	{
		Configuration cfg = new Configuration(EssCore.getInstance(), "messages.yml");
		if(cfg.getString("CommandUsages." + command) != null) return cfg.getString("CommandUsages." + command);
		else return cfg.getString("CommandUsages.CommandUsageWasntFound").replace("%cmd", command);
	}
}
