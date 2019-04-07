package pl.piotrschodzinski.controller.vehicle;

import pl.piotrschodzinski.dao.CustomerDao;
import pl.piotrschodzinski.dao.VehicleDao;
import pl.piotrschodzinski.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/EditVehicle")
public class EditVehicle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        int manufactureYear = Integer.parseInt(request.getParameter("manufactureYear"));
        String registrationNumber = request.getParameter("registrationNumber");
        LocalDate serviceDate = LocalDate.parse(request.getParameter("serviceDate"));
        int customerId = Integer.parseInt(request.getParameter("customer"));
        Vehicle vehicle = new Vehicle(brand, model, manufactureYear, registrationNumber, serviceDate, customerId);
        VehicleDao.getInstance().update(vehicle, id);
        response.sendRedirect("ShowVehicles");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("vehicle", VehicleDao.getInstance().readById(id));
        request.setAttribute("currentYear", LocalDate.now().getYear());
        request.setAttribute("customers", CustomerDao.getInstance().readAll());
        getServletContext().getRequestDispatcher("/edit_vehicle.jsp").forward(request, response);
    }
}
