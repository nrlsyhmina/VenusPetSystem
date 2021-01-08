package controller;

import java.io.IOException;
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
 * Servlet implementation class DeleteServiceController
 */
@WebServlet("/DeleteServiceController")
public class DeleteServiceController extends HttpServlet {
	   private static final long serialVersionUID = 1L;
	    private ServiceDAO dao; 
	    
	    public DeleteServiceController() {
	        super();
	        dao = new ServiceDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	        String action = request.getParameter("action");
	        if (action.equalsIgnoreCase("delete")){
	        int serviceID= Integer.parseInt(request.getParameter("serviceID"));
	        dao.deleteService(serviceID);
	        
	        action = "listService.jsp";
	        try {
				request.setAttribute("service", ServiceDAO.getServiceList());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	        RequestDispatcher view = request.getRequestDispatcher("listService.jsp");
	        view.forward(request, response);
	    }

}
