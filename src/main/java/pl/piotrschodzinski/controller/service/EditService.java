package pl.piotrschodzinski.controller.service;

import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.dao.VehicleDao;
import pl.piotrschodzinski.dao.WorkerDao;
import pl.piotrschodzinski.model.Service;
import pl.piotrschodzinski.model.ServiceStatus;
import pl.piotrschodzinski.model.Vehicle;
import pl.piotrschodzinski.model.Worker;
import pl.piotrschodzinski.util.Tools;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/EditService")
public class EditService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String status = request.getParameter("status");
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        Service service = ServiceDao.getInstance().readById(serviceId);
        if (status.equals("cancelled")) {
            service.setStatus(ServiceStatus.cancelled);
            ServiceDao.getInstance().update(service, serviceId);
            response.sendRedirect("ShowCurrentServices");
        } else if (status.equals("completed")) {
            service.setStatus(ServiceStatus.completed);
            ServiceDao.getInstance().update(service, serviceId);
            response.sendRedirect("ShowCurrentServices");
        } else {
            ArrayList<Worker> workers = WorkerDao.getInstance().readAll();
            if (service.getWorkerId() != 0) {
                Tools.swapWorkerElements(workers, WorkerDao.getInstance().readById(service.getWorkerId()));
            }
            ArrayList<Vehicle> vehicles = VehicleDao.getInstance().readAll();
            if (service.getVehicleId() != 0) {
                Tools.swapVehicleElements(vehicles, VehicleDao.getInstance().readById(service.getVehicleId()));
            }
            ArrayList<String> statuses = ServiceStatus.getStatuses();
            Tools.swapStatusElements(statuses, status);
            request.setAttribute("status", status);
            request.setAttribute("service", service);
            request.setAttribute("statuses", statuses);
            request.setAttribute("workers", workers);
            request.setAttribute("vehicles", vehicles);
            getServletContext().getRequestDispatcher("/edit_service.jsp").forward(request, response);
        }
    }
}
