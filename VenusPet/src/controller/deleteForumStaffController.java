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
 * Servlet implementation class deleteForumAdminController
 */
@WebServlet("/deleteForumStaffController")
public class deleteForumStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private forumDAO dao; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteForumStaffController() {
        super();
        dao = new forumDAO();
        // TODO Auto-generated constructor stub
    }



    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
        int forumID= Integer.parseInt(request.getParameter("forumID"));
        dao.deleteForum(forumID);
        
        action = "customer-forum.jsp";
        try {
			request.setAttribute("forum", forumDAO.getAllForum());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        RequestDispatcher view = request.getRequestDispatcher("staff-forum.jsp");
        view.forward(request, response);
    }

}

