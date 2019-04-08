package pl.piotrschodzinski.controller.customer;

import pl.piotrschodzinski.dao.CustomerDao;
import pl.piotrschodzinski.dao.ServiceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowCustomerServices")
public class ShowCustomerServices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("services", ServiceDao.getInstance().readCustomerServices(id));
        request.setAttribute("customer", CustomerDao.getInstance().readById(id));
        getServletContext().getRequestDispatcher("/customer_services.jsp").forward(request, response);
    }
}
