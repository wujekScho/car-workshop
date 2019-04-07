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
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/ShowWorkerServices")
public class ShowWorkerServices extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ArrayList<CurrentService> workerServices = ServiceDao.getInstance().readWorkerServices(id);
        if (!workerServices.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            StringBuilder json = new StringBuilder();
            json.append("{");
            for (CurrentService service : workerServices) {
                json.append(objectMapper.writeValueAsString(service)).append(",");
            }
            json.setLength(json.length() - 1);
            json.append("}");
            out.print(json);
        }
        out.flush();
    }
}
