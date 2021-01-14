package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.forumDAO;
import model.Forum;


/**
 * Servlet implementation class ViewForumController
 */
@WebServlet("/viewForumStaffController")
public class viewForumStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		List<Forum> fo = forumDAO.getAllForum();
			request.setAttribute("forum", fo);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("staff-forum.jsp");
		dispatcher.forward(request, response);
    }
}