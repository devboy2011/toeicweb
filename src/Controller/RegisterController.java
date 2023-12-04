package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Member;
import DAO.RegisterDAO;
import DB.DBConnection;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding("UTF-8");
		}
		
		Connection conn = DBConnection.CreateConnection();
		
		String name = request.getParameter("name");
		String membername = request.getParameter("membername");
		String memberpass = request.getParameter("memberpass");
		int categorymemberid = 2;
		
		Member member = new Member();
		member.setName(name);
		member.setMembername(membername);
		member.setMemberpass(memberpass);
		
		boolean test = RegisterDAO.InsertAcount(request, conn, member);
		
		if (test) {
			request.setAttribute("msgregister", "Đăng ký thành công");
		} else {
			request.setAttribute("msgregister", "Đăng ký không thành công");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("RegisterForward");
		rd.forward(request, response);
		
		
	}

}
