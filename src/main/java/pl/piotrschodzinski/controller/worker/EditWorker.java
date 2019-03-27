package pl.piotrschodzinski.controller.worker;

import pl.piotrschodzinski.dao.WorkerDao;
import pl.piotrschodzinski.model.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EditWorker")
public class EditWorker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String note = request.getParameter("note");
        Double ratePerHour = Double.parseDouble(request.getParameter("ratePerHour"));
        Worker worker = new Worker(name, surname, address, phoneNumber, note, ratePerHour);
        WorkerDao.getInstance().update(worker, id);
        response.sendRedirect("ShowWorkers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Worker worker = WorkerDao.getInstance().readById(id);
        request.setAttribute("worker", worker);
        getServletContext().getRequestDispatcher("/edit_worker.jsp").forward(request, response);
    }
}
