package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineDAO;
import DB.DBConnection;

/**
 * Servlet implementation class GrammarGuidelineContentController
 */
@WebServlet("/GrammarGuidelineContentController")
public class GrammarGuidelineContentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrammarGuidelineContentController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding()==null)
		{
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DBConnection.CreateConnection();
		
		String content = request.getParameter("content");
		String grammarguidelineidstr = request.getParameter("grammarguidelineid");
		
		int grammarguidelineid = Integer.parseInt(grammarguidelineidstr);
		
		GrammarGuideline grammarguideline = new GrammarGuideline();
		
		grammarguideline.setContent(content);
		
		
		boolean kt = GrammarGuidelineDAO.InsertGrammarGuideline(request, conn, grammarguideline,grammarguidelineid);
		if (kt)
		{
			RequestDispatcher rd = request.getRequestDispatcher("GrammarGuideForward");
			rd.forward(request,response);
		}
		else 
		{	
			request.setAttribute("msggrammarguidelinecontent","Thêm nội dung không thành công");
			request.setAttribute("grammarguidelineid",grammarguidelineid);
			RequestDispatcher rd = request.getRequestDispatcher("View/Admin/InsertGrammarGuidelineContent.jsp");
			rd.forward(request,response);	
		}
	}

}
