package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import model.Customer;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try { 
			Customer customer = new Customer();
			customer.setcEmail(request.getParameter("cEmail"));
			customer.setcPassword(request.getParameter("cPassword"));
			customer = CustomerDAO.login(customer);
			if (customer.isValid()) { // logged-in page
				HttpSession session = request.getSession(true);
				session.setAttribute("uName", customer.getcName());
				session.setAttribute("uEmail", customer.getcPassword());
				request.setAttribute("customerList", CustomerDAO.getAllCustomer() );
				RequestDispatcher rd = request.getRequestDispatcher("listCustomer.jsp");
				rd.forward(request, response);
			} else response.sendRedirect("login.jsp"); // error page 
		}
		catch(Throwable theException)
		{
			System.out.println(theException);
		}
	}

}
