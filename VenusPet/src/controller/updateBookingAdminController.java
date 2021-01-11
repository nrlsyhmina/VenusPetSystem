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
 * Servlet implementation class updateBookingAdminController
 */
@WebServlet("/updateBookingAdminController")
public class updateBookingAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private bookingDAO dao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateBookingAdminController() {
    	dao = new bookingDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingID = Integer.parseInt(request.getParameter("bookingID"));
        Booking book = dao.getBookingById(bookingID); 
        request.setAttribute("booking", book);
        RequestDispatcher view = request.getRequestDispatcher("customer-rate.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Booking book = new Booking();
		book.setBookingID(Integer.parseInt(request.getParameter("bookingID")));
		book.setCustID(Integer.parseInt(request.getParameter("custID")));
		
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

		book.setServiceID(Integer.parseInt(request.getParameter("serviceID")));
		book.setbTotalPrice(Double.parseDouble(request.getParameter("bTotalPrice")));
		book.setRating(Integer.parseInt(request.getParameter("rating")));
		dao.updateBooking(book);
		
		try {
			request.setAttribute("booking", bookingDAO.getAllBooking());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		RequestDispatcher dispatcher = request.getRequestDispatcher("admin-update-booking.jsp");
		dispatcher.forward(request, response);
	}

}
