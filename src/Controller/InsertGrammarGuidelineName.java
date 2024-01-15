package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineDAO;
import DB.DBConnection;


@WebServlet("/InsertGrammarGuidelineName")
public class InsertGrammarGuidelineName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertGrammarGuidelineName() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		
		String grammarname = request.getParameter("grammarname");
		
		GrammarGuideline grammarguideline = new GrammarGuideline();
		grammarguideline.setGrammarname(grammarname);
		
		Connection conn = DBConnection.CreateConnection();
		try 
		{
			boolean kt = GrammarGuidelineDAO.InsertName(request, conn, grammarguideline);
			
			if (kt)
			{
				int grammarguidelineid = GrammarGuidelineDAO.GetGrammarGuidelineById(request, conn, grammarguideline);
				request.setAttribute("grammarguidelineid", grammarguidelineid);
				
				RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertGrammarGuidelineImage.jsp");
				rd.forward(request,response);
			}
			else
			{
				request.setAttribute("msglistgrammarguidelinemanage","Thêm không thành công");
				RequestDispatcher rd = request.getRequestDispatcher("GrammarGuideForward");
				rd.forward(request,response);
			}
			
			conn.close();
		} 
		catch (SQLException e) 
		{	
			request.setAttribute("msglistgrammarguidelinemanage",e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
			rd.forward(request,response);
		}
		
	}

}
