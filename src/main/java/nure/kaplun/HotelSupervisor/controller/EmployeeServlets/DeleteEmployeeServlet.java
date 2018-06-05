package nure.kaplun.HotelSupervisor.controller.EmployeeServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Anton on 05.06.2018.
 */
@WebServlet(name = "DeleteEmployeeServlet",  urlPatterns={"/deleteEmployee"})
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int employeeId = Integer.parseInt(req.getParameter("employeeId"));
        Connection connection = DataBaseConnector.openConnection();
        EmployeesRepository employeesRepository = new EmployeesRepository(connection);
        boolean isEmployeeDeleted = employeesRepository.deleteEmployeeById(employeeId);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(resp, isEmployeeDeleted);
    }
}
