package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class RegisterDAO {
	
	public static boolean InsertAcount (HttpServletRequest request, Connection conn, Member acc) 
	{
		PreparedStatement ptmt = null; 
		
		String sql = "insert into member(name, membername, memberpass, categorymemberid) values (?, ?, ?, ?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String name = acc.getName();
			String membername = acc.getMembername();
			String memberpass = acc.getMemberpass();
			int categorymemberid = 1;
			
			ptmt.setString(1, name);
			ptmt.setString(2, membername);
			ptmt.setString(3, memberpass);
			ptmt.setInt(4, categorymemberid);
			
			int kt = ptmt.executeUpdate();
			
			if (kt != 0) {
				return true;
			} 
			
			ptmt.close();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
}
