package pl.piotrschodzinski.controller.worker;

import pl.piotrschodzinski.dao.WorkerDao;
import pl.piotrschodzinski.model.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddWorker")
public class AddWorker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String note = request.getParameter("note");
        Double ratePerHour = Double.parseDouble(request.getParameter("ratePerHour"));
        Worker worker = new Worker(name, surname, address, phoneNumber, note, ratePerHour);
        WorkerDao.getInstance().create(worker);
        response.sendRedirect("ShowWorkers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/add_worker.jsp").forward(request, response);
    }
}
