package org.mess.repository;

import java.sql.*;

import org.mess.helper.PathHelper;

//import com.mysql.cj.jdbc.Driver;


public class DBConfig {
	protected Connection conn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	public DBConfig() {
		try {
			
			PathHelper phelp=new PathHelper();
			Class.forName(PathHelper.p.getProperty("driver"));
			conn = DriverManager.getConnection(PathHelper.p.getProperty("url"), PathHelper.p.getProperty("user"), PathHelper.p.getProperty("pass"));
//			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
//			DriverManager.registerDriver(d);
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/messmanagement","root","sudhir");
			
			} catch (Exception e) {
			System.out.println("Error " + e);
		}
	}
	
}
