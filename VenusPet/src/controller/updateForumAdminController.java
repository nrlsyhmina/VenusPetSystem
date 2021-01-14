package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.forumDAO;
import model.Forum;

/**
 * Servlet implementation class updateForumAdminController
 */
@WebServlet("/updateForumAdminController")
public class updateForumAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private forumDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateForumAdminController() {
    	dao = new forumDAO();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int forumID = Integer.parseInt(request.getParameter("forumID"));
        Forum fo = dao.getForumById(forumID); 
        request.setAttribute("forum", fo);
        RequestDispatcher view = request.getRequestDispatcher("admin-update-forum.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Forum fo = new Forum();
		fo.setForumID(Integer.parseInt(request.getParameter("forumID")));
		fo.setForumTitle(request.getParameter("forumTitle"));
		fo.setForumDescription(request.getParameter("forumDescription"));
		fo.setCustID(Integer.parseInt(request.getParameter("custID")));
		fo.setStaffID(request.getParameter("staffID"));
		
		dao.updateForum(fo);
		
		try {
			request.setAttribute("forum", forumDAO.getAllForum());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-forum.jsp");
		dispatcher.forward(request, response);
	}
}
	