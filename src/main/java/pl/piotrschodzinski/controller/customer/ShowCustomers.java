package pl.piotrschodzinski.controller.customer;

import pl.piotrschodzinski.dao.CustomerDao;
import pl.piotrschodzinski.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowCustomers")
public class ShowCustomers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Customer> customers = CustomerDao.getInstance().readAll();
        request.setAttribute("customers", customers);
        getServletContext().getRequestDispatcher("/customers.jsp").forward(request, response);
    }
}
