package com.revature.dsaebank_prst.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//first three interfaces required for JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static ConnectionFactory cf = null;
	
	private ConnectionFactory() {
		
	}
	
	//synch to prevent issues stemming from race conditions
	public static synchronized ConnectionFactory getInstance() {
		// ensure that cf can be initialized only if it is null, otherwise it is permanently set
		if (cf == null) cf = new ConnectionFactory();
		return cf;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Properties prop = new Properties();
			prop.load(new FileReader("resources/database.properties"));
			
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("usr"),
					prop.getProperty("psw"));	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}