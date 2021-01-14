package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.forumDAO;
import model.Forum;

/**
 * Servlet implementation class addForumCustomerController
 */
@WebServlet("/addForumCustomerController")
public class addForumCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private forumDAO dao;
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
	    public addForumCustomerController() {
	    	super();
			dao = new forumDAO();
		}
	    
	  
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Forum fo = new Forum();
		
		
		fo.setForumTitle(request.getParameter("forumTitle"));
		
		String date = request.getParameter("forumDate");
		Date newDate;
		try {
		newDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
		fo.setForumDate(newDate);
		} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		fo.setCustID(Integer.parseInt(request.getParameter("custID")));
		
		
		dao.addForum(fo);
		
		try {
			request.setAttribute("forum", forumDAO.getAllForum());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher("customer-forum.jsp");
		dispatcher.forward(request, response);
	
}

}
