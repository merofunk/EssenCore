


/* -------------------------------------------------------------------------
 * Code used from SpigotMC threads. Author: Logout400
 * 
 * Link: https://www.spigotmc.org/threads/class-simple-custom-configs.51124/
 * -------------------------------------------------------------------------
 */


package vk.com.merofunk.esscore.configurations;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Configuration extends YamlConfiguration
{
	private File file;
	private String defaults;
	private JavaPlugin plugin;
	
	public Configuration(JavaPlugin plugin, String filename)
	{
		this(plugin, filename, null);
	}
	
	public Configuration(JavaPlugin plugin, String filename, String defaults)
	{
		this.plugin = plugin;
		this.defaults = defaults;
		this.file = new File(plugin.getDataFolder(), filename);
		reload();
	}
	
	public void reload()
	{
		if(!file.exists())
		{
			try
			{
				file.getParentFile().mkdirs();
				file.createNewFile();
			}catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
		
		try
		{
			load(file);
			if(defaults != null)
			{
				InputStreamReader read = new InputStreamReader(plugin.getResource(defaults));
				FileConfiguration defConf = YamlConfiguration.loadConfiguration(read);
				setDefaults(defConf);
				options().copyDefaults(true);
				read.close();
				save();
			}
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch(InvalidConfigurationException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void save()
	{
		try
		{
			options().indent(2);
			save(file);
		}catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
