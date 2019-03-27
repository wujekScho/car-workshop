package pl.piotrschodzinski.controller.customer;

import pl.piotrschodzinski.dao.CustomerDao;
import pl.piotrschodzinski.model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/EditCustomer")
public class EditCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate birthDate = null;
        if (!request.getParameter("birthDate").isEmpty()) {
            birthDate = LocalDate.parse(request.getParameter("birthDate"));
        }
        Customer customer = new Customer(name, surname, birthDate);
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerDao.getInstance().update(customer, id);
        response.sendRedirect("ShowCustomers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = CustomerDao.getInstance().readById(id);
        request.setAttribute("customer", customer);
        getServletContext().getRequestDispatcher("/edit_customer.jsp").forward(request, response);
    }
}
