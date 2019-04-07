package pl.piotrschodzinski.controller.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pl.piotrschodzinski.dao.ServiceDao;
import pl.piotrschodzinski.model.CurrentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ShowWorkerServices")
public class ShowWorkerServices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        ArrayList<CurrentService> workerServices = ServiceDao.getInstance().readWorkerServices(id);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(workerServices);
        response.getWriter().append(json);
    }
}
