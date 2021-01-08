package controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import dao.CustomerDAO;

@WebServlet("/ListCustomerController")
public class ListCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CustomerDAO dao;
    
    public ListCustomerController() {
        super();
        dao = new CustomerDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int custID = Integer.parseInt(request.getParameter("custID"));//No Function
        
        request.setAttribute("customer", CustomerDAO.getAllCustomer());
        RequestDispatcher view = request.getRequestDispatcher("listCustomer.jsp");
        view.forward(request, response);
    }
}