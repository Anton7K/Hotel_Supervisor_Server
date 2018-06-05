package nure.kaplun.HotelSupervisor.controller.EmployeeServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Employee;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;
import nure.kaplun.HotelSupervisor.model.Room;

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
@WebServlet(name = "GetEmployeeByIdServlet",  urlPatterns={"/getEmployeeById"})
public class GetEmployeeByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Connection connection = DataBaseConnector.openConnection();
        EmployeesRepository employeesRepository = new EmployeesRepository(connection);
        Employee employee = employeesRepository.getEmployeeById(employeeId);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(response, employee);
    }
}