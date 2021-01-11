package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bookingDAO;

/**
 * Servlet implementation class deleteBookingAdminController
 */
@WebServlet("/deleteBookingAdminController")
public class deleteBookingAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private bookingDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBookingAdminController() {
    	super();
        dao = new bookingDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")){
        int bookingID= Integer.parseInt(request.getParameter("bookingID"));
        dao.deleteBooking(bookingID);
        
        action = "customer-rate.jsp";
        try {
			request.setAttribute("booking", bookingDAO.getAllBooking());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        RequestDispatcher view = request.getRequestDispatcher("admin-booking.jsp");
        view.forward(request, response);
    }

}
