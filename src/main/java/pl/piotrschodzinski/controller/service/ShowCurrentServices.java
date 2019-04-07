package pl.piotrschodzinski.controller.service;

import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.model.CurrentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowCurrentServices")
public class ShowCurrentServices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<CurrentService> currentServices = ServiceDao.getInstance().readAllCurrent(10);
        request.setAttribute("services", currentServices);
        getServletContext().getRequestDispatcher("/current_services.jsp").forward(request, response);


    }
}
