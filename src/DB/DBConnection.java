package DB;

import java.sql.*;

public class DBConnection 
{
	public static Connection CreateConnection()
	{
		Connection conn = null;
		
		//String url = "jdbc:mysql://localhost:3307/toeicweb?useUnicode=true&amp;characterEncoding=utf8";
		//String username = "root";
		//String password = "123456";
		
		String url = "jdbc:mysql://localhost:3307/toeicweb";
		String username = "root";
		String password = "123456";
		
		try 
		{
			//load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//create connection
			conn = DriverManager.getConnection(url, username, password);
			
		} 
		catch (ClassNotFoundException e) 
		{

			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}	
		return conn;
	}
}
