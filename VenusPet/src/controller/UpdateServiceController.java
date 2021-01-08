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
 * Servlet implementation class UpdateServiceController
 */
@WebServlet("/UpdateServiceController")
public class UpdateServiceController extends HttpServlet {
	private ServiceDAO dao;
    public UpdateServiceController() {
    	dao = new ServiceDAO();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int serviceID = Integer.parseInt(request.getParameter("serviceID"));
        Service s = dao.getServiceById(serviceID); 
        request.setAttribute("service", s);
        RequestDispatcher view = request.getRequestDispatcher("updateService.jsp");
        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Service sv = new Service();
		 sv.setSvName(request.getParameter("svName"));
		 sv.setSvPrice(Double.parseDouble(request.getParameter("svPrice")));
	        sv.setSvDescription(request.getParameter("svDescription"));
	        sv.setStaffID(request.getParameter("staffID"));
	        dao.updateService(sv);//DAO
	        
	        try {
				request.setAttribute("service", ServiceDAO.getServiceList());
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        RequestDispatcher view = request.getRequestDispatcher("listService.jsp");
	        view.forward(request, response);
	}

}
