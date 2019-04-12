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
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/EditService")
public class EditService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = new Service();
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        service.setVehicleId(vehicleId);
        String problemDescription = request.getParameter("problemDescription");
        service.setProblemDescription(problemDescription);
        String status = request.getParameter("status");
        Tools.setServiceStatus(service, status);
        service.setRecived(LocalDate.parse(request.getParameter("recived")));
        if (!request.getParameter("plannedRepairDate").isEmpty()) {
            LocalDate plannedRepairDate = LocalDate.parse(request.getParameter("plannedRepairDate"));
            service.setPlannedRepairDate(plannedRepairDate);
        }
        if (!request.getParameter("repairCost").isEmpty()) {
            double repairCost = Double.parseDouble(request.getParameter("repairCost"));
            service.setRepairCost(repairCost);
        }
        if (!request.getParameter("workerId").isEmpty()) {
            int workerId = Integer.parseInt(request.getParameter("workerId"));
            Worker worker = WorkerDao.getInstance().readById(workerId);
            service.setWorkerId(workerId);
            service.setRatePerHour(worker.getRatePerHour());
        }
        if (!request.getParameter("repairDate").isEmpty()) {
            LocalDate repairDate = LocalDate.parse(request.getParameter("repairDate"));
            service.setRepairDate(repairDate);
        }
        if (!request.getParameter("repairDescription").isEmpty()) {
            String repairDescription = request.getParameter("repairDescription");
            service.setRepairDescription(repairDescription);
        }
        if (!request.getParameter("partsCost").isEmpty()) {
            Double partsCost = Double.parseDouble(request.getParameter("partsCost"));
            service.setPartsCost(partsCost);
        }
        if (!request.getParameter("workHours").isEmpty()) {
            int workHours = Integer.parseInt(request.getParameter("workHours"));
            service.setWorkHours(workHours);
        }
        ServiceDao.getInstance().update(service, serviceId);
        response.sendRedirect("ShowCurrentServices");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("chosenStatus");
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));
        Service service = ServiceDao.getInstance().readById(serviceId);
        if (status.equals("cancelled")) {
            service.setStatus(ServiceStatus.cancelled);
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
