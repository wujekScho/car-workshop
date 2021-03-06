package pl.piotrschodzinski.controller.service;

import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.dao.WorkerDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowWorkerServices")
public class ShowWorkerServices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("services", ServiceDao.getInstance().readWorkerServices(id));
        request.setAttribute("worker", WorkerDao.getInstance().readById(id));
        getServletContext().getRequestDispatcher("/worker_services.jsp").forward(request, response);
    }
}
