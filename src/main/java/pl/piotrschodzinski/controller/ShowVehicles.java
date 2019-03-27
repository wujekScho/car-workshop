package pl.piotrschodzinski.controller;

import pl.piotrschodzinski.dao.VehicleDao;
import pl.piotrschodzinski.model.Vehicle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowVehicles")
public class ShowVehicles extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Vehicle> vehicles = VehicleDao.getInstance().readAll();
        request.setAttribute("vehicles", vehicles);
        getServletContext().getRequestDispatcher("/vehicles.jsp").forward(request, response);
    }
}
