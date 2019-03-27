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

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        LocalDate birthDate = null;
        if (!request.getParameter("birthDate").isEmpty()) {
            System.out.println(request.getParameter("birthDate"));
            birthDate = LocalDate.parse(request.getParameter("birthDate"));
        }
        Customer customer = new Customer(name, surname, birthDate);
        System.out.println(customer.getBirthDate());
        CustomerDao.getInstance().create(customer);
        response.sendRedirect("ShowCustomers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        getServletContext().getRequestDispatcher("/add_customer.jsp").forward(request, response);
        Customer customer = new Customer("jan", "aaaa", LocalDate.of(2019, 03, 03));
        CustomerDao.getInstance().create(customer);
        response.sendRedirect("ShowCustomers");
    }
}
