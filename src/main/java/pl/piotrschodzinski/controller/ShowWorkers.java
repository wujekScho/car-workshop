package pl.piotrschodzinski.controller;

import pl.piotrschodzinski.dao.WorkerDao;
import pl.piotrschodzinski.model.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowWorkers")
public class ShowWorkers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Worker> workers = WorkerDao.getInstance().readAll();
        request.setAttribute("workers", workers);
        getServletContext().getRequestDispatcher("/workers.jsp").forward(request, response);
    }
}
