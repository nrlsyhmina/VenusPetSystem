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

import dao.bookingDAO;
import model.Booking;

/**
 * Servlet implementation class addBookingStaffController
 */
@WebServlet("/addBookingStaffController")
public class addBookingStaffController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private bookingDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addBookingStaffController() {
    	super();
		dao = new bookingDAO();
	}
	

 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Booking book = new Booking();
			
			
			String date = request.getParameter("bDate");
			Date newDate;
			try {
			newDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			book.setbDate(newDate);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			book.setbTime(request.getParameter("bTime"));

			book.setbTotalPrice(Double.parseDouble(request.getParameter("bTotalPrice")));
			
			book.setCustID(Integer.parseInt(request.getParameter("custID")));
			
			book.setServiceID(Integer.parseInt(request.getParameter("serviceID")));
			
			book.setStaffID(request.getParameter("staffID"));
			dao.addBooking(book);
			
			try {
				request.setAttribute("booking", bookingDAO.getAllBooking());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			RequestDispatcher dispatcher = request.getRequestDispatcher("staff-booking.jsp");
			dispatcher.forward(request, response);
		
	}

}

