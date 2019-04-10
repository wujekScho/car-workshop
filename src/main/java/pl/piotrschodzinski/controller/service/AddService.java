package pl.piotrschodzinski.controller.service;

import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.dao.VehicleDao;
import pl.piotrschodzinski.model.Service;
import pl.piotrschodzinski.model.ServiceStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/AddService")
public class AddService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String problemDescription = request.getParameter("problemDescription");
        Service service = new Service();
        service.setRecived(LocalDate.now());
        service.setStatus(ServiceStatus.accepted);
        service.setVehicleId(vehicleId);
        service.setProblemDescription(problemDescription);
        ServiceDao.getInstance().create(service);
        response.sendRedirect("ShowCurrentServices");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("vehicles", VehicleDao.getInstance().readAll());
        getServletContext().getRequestDispatcher("/add_service.jsp").forward(request, response);
    }
}
