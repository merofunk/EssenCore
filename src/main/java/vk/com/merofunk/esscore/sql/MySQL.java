package vk.com.merofunk.esscore.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import vk.com.merofunk.esscore.EssCore;

public class MySQL 
{
	private Connection conn;
	private String host,user,database,password;
	private int port;
	
	public MySQL(Connection conn, String host, int port, String user, String password, String database)
	{
		this.conn = conn;
		this.host = host;
		this.user = user;
		this.password = password;
		this.database = database;
		this.port = port;
	}
	
	public void openConnection() throws SQLException, ClassNotFoundException
	{
		if(conn != null && !conn.isClosed()) return;
		
		synchronized(EssCore.getInstance())
		{
			if(conn != null && !conn.isClosed()) return;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
		}
	}
}
