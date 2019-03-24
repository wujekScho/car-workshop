package pl.piotrschodzinski.controller;

import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.model.CurrentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/HomePanel")
public class HomePanel extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<CurrentService> currentServices = ServiceDao.getInstance().readCurrent(50);
        for (CurrentService service : currentServices) {
            System.out.println(service);
        }
        request.setAttribute("services", currentServices);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
