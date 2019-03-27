package pl.piotrschodzinski.controller;

import pl.piotrschodzinski.dao.CustomerDao;
import pl.piotrschodzinski.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowCustomerDetails")
public class ShowCustomerDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = CustomerDao.getInstance().readById(id);
        request.setAttribute("customer", customer);
        getServletContext().getRequestDispatcher("/customer_details.jsp").forward(request, response);
    }
}
