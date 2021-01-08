package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
	private ServiceDAO dao;
	    public AddServiceController() {
	    	dao = new ServiceDAO();
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//retrieve input and set values
        Service service = new Service();
        
        try {
        	service.setSvName(request.getParameter("svName"));
	        service.setSvPrice(Double.parseDouble(request.getParameter("svPrice")));
	        service.setSvDescription(request.getParameter("svDescription"));
	        service.setStaffID(request.getParameter("staffID"));
	        
        } catch(NumberFormatException ex){
        	     return; 
        	  }
 
        dao.addService(service);
        
		try {
			request.setAttribute("service", ServiceDAO.getServiceList());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("listService.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
