package pl.piotrschodzinski;

import pl.piotrschodzinski.dao.WorkerDao;
import pl.piotrschodzinski.model.Worker;
import pl.piotrschodzinski.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Worker worker = new Worker("Piotr", "Schodziński", "Janowska 82, Biłgoraj", "784 076 902", "bardzo dobry pracownik", 100.99);
        try {
            Connection connection = DBUtil.getConn();
            WorkerDao.createWorker(connection, worker);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
