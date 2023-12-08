package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import BEAN.Member;

public class LoginDAO {
	
	public static boolean AuthenticateMember (HttpServletRequest request, Connection conn, Member acc) 
	{
		PreparedStatement ptmt = null; 
		
		String sql = "select * from member where membername = ? and memberpass = ?";

		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String membername = acc.getMembername();
			String memberpass = acc.getMemberpass();

			
			ptmt.setString(1, membername);
			ptmt.setString(2, memberpass);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.next()) {
				return true;
			} 
			
			ptmt.close();
		} 
		
		catch (SQLException e) {
			//e.printStackTrace();
			request.setAttribute("msglogin", e.getMessage());
		}
		
		return false;
	}
	
	public static int AuthorizeMember (HttpServletRequest request, Connection conn, Member acc)  
	{
		
		PreparedStatement ptmt = null; 
		
		String sql = "select * from member where membername = ? and memberpass = ?";
		int categorymemberid = 0;
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String membername = acc.getMembername();
			String memberpass = acc.getMemberpass();

			ptmt.setString(1, membername);
			ptmt.setString(2, memberpass);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.next()) {
				categorymemberid = rs.getInt("categorymemberid");
				acc.setCategorymemberid(categorymemberid);
			} 
			
			ptmt.close();

		}
		catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
		}
		
		return categorymemberid;
		
	}
	
	
	public static String GetName (HttpServletRequest request, Connection conn, Member acc) 
	{
		PreparedStatement ptmt = null; 
		
		String sql = "select * from member where membername = ? and memberpass = ?";
		String name = "";
		
		try {
			ptmt = conn.prepareStatement(sql);
			
			String membername = acc.getMembername();
			String memberpass = acc.getMemberpass();

			ptmt.setString(1, membername);
			ptmt.setString(2, memberpass);
			
			ResultSet rs = ptmt.executeQuery();
			
			if (rs.next()) {
				name = rs.getString("name");
			} 
			
			ptmt.close();

		}
		catch (SQLException e) {
			request.setAttribute("msglogin", e.getMessage());
		}
		
		return name;
	}
}
