package controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ServiceDAO;
import model.Service;

/**
 * Servlet implementation class AddServiceController
 */
@WebServlet("/AddServiceController")
public class AddServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServiceDAO dao;
	
	public AddServiceController()
	{
		super();
		dao = new ServiceDAO();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int serviceID = Integer.parseInt(request.getParameter("serviceID"));
		 
		RequestDispatcher dispatcher = request.getRequestDispatcher("add-service.jsp");
		dispatcher.forward(request, response);
	
	}
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        //retrieve input and set values
	        Service service = new Service();
	        
	        service.setSvName(request.getParameter("svName"));
	        service.setSvPrice(Double.parseDouble(request.getParameter("svPrice")));
	        service.setSvDescription(request.getParameter("svDescription"));
	        service.setStaffID(Integer.parseInt(request.getParameter("staffID")));
	    
	        dao.addService(service);
	        
			request.setAttribute("service", ServiceDAO.getAllService());
			RequestDispatcher dispatcher = request.getRequestDispatcher("view_service.jsp");
			dispatcher.forward(request, response);
			

	        }



}
