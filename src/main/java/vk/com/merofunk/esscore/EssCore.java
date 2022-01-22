package vk.com.merofunk.esscore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import vk.com.merofunk.esscore.chat.Format;
import vk.com.merofunk.esscore.commands.BanCommand;
import vk.com.merofunk.esscore.commands.BroadcastCommand;
import vk.com.merofunk.esscore.commands.ConfigReload;
import vk.com.merofunk.esscore.commands.GameModeCommand;
import vk.com.merofunk.esscore.commands.GetStringFromConf;
import vk.com.merofunk.esscore.commands.NameTagCommand;
import vk.com.merofunk.esscore.commands.NicknameCommand;
import vk.com.merofunk.esscore.commands.PrefixCommand;
import vk.com.merofunk.esscore.commands.TabListNameCommand;
import vk.com.merofunk.esscore.configurations.Configuration;
import vk.com.merofunk.esscore.events.Join;
import vk.com.merofunk.esscore.events.PrefixJoin;
import vk.com.merofunk.esscore.sql.MySQL;
import net.milkbowl.vault.chat.Chat;

public class EssCore extends JavaPlugin
{
	private static EssCore instance;
	private Chat chat = null;
	private Connection connection;
	private static Scoreboard scoreboard;
	@SuppressWarnings("unused")
	private Configuration customConf;
	
	public void onEnable()
	{
		scoreboard = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
		customConf = new Configuration(this, "messages.yml", "messages.yml");
		instance = this;
		setupChat();
		registerEvents(this);
		registerDefaultConfiguration();
		setCommandExecutors();
		initSQL();
	}
	
	public void onDisable()
	{
		saveDefaultConfig();
	}
	
	public static EssCore getInstance() { return instance; }
	
	private void registerDefaultConfiguration()
	{
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	private void setCommandExecutors()
	{
		this.getCommand("configtest").setExecutor(new GetStringFromConf());
		this.getCommand("gamemode").setExecutor(new GameModeCommand());
		this.getCommand("gm").setExecutor(new GameModeCommand());
		this.getCommand("ban").setExecutor(new BanCommand());
		this.getCommand("configrel").setExecutor(new ConfigReload());
		this.getCommand("confrel").setExecutor(new ConfigReload());
		this.getCommand("broadcast").setExecutor(new BroadcastCommand());
		this.getCommand("prefix").setExecutor(new PrefixCommand());
		this.getCommand("nick").setExecutor(new NicknameCommand());
		this.getCommand("tabname").setExecutor(new TabListNameCommand());
		this.getCommand("nametag").setExecutor(new NameTagCommand());
	}
	
	@SuppressWarnings("unused")
	private void initSQL()
	{
		if(getConfig().getBoolean("Global.MySQL.Use"))
		{	
			MySQL sql = new MySQL(connection, getConfig().getString("Global.MySQL.Hostname"), getConfig().getInt("Global.MySQL.Port"), 
					getConfig().getString("Global.MySQL.Username"), getConfig().getString("Global.MySQL.Password"), 
					getConfig().getString("Global.MySQL.Database"));
			try
			{
				getLogger().log(Level.INFO, "Trying to connect to " + getConfig().getString("Global.MySQL.Hostname") + "@" + 
			getConfig().getString("Global.MySQL.Username") + "/" + getConfig().getString("Global.MySQL.Database") + " database...");
				sql.openConnection();
				Statement statement = connection.createStatement();
			}
			catch(ClassNotFoundException ex)
			{
				ex.printStackTrace();
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			getLogger().log(Level.INFO, "MySQL feature in configuration is disabled.");
		}
	}
	
	public boolean SQLEnabled()
	{
		return getConfig().getBoolean("Global.MySQL.Use");
	}
	
	public static Scoreboard getMainScoreboard()
	{
		return scoreboard;
	}
	
	public Chat getChat()
	{
		return chat;
	}
	
	private boolean setupChat()
	{
		RegisteredServiceProvider<Chat> provider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if(provider != null) chat = provider.getProvider();
		return (chat != null);
	}
	
	private void registerEvents(JavaPlugin plugin)
	{
		Bukkit.getServer().getPluginManager().registerEvents(new Format(), plugin);
		Bukkit.getServer().getPluginManager().registerEvents(new Join(), plugin);
		Bukkit.getServer().getPluginManager().registerEvents(new PrefixJoin(), plugin);
	}
}
