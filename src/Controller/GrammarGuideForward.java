package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.GrammarGuideline;
import DAO.GrammarGuidelineDAO;
import DB.DBConnection;

@WebServlet("/GrammarGuideForward")
public class GrammarGuideForward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrammarGuideForward() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.CreateConnection();
		
		int start = request.getIntHeader("start");
		int count = request.getIntHeader("count");
		
		List<GrammarGuideline> grammarguideline = GrammarGuidelineDAO.DisplayManage(request, start, count, conn);
		
		request.setAttribute("listgrammarguidelinemanage", grammarguideline);
		
		RequestDispatcher rd = request.getRequestDispatcher("View/Admin/GrammarGuide.jsp");
		rd.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
