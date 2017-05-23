package nure.kaplun.HotelSupervisor.controller.EmployeeServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Employee;
import nure.kaplun.HotelSupervisor.model.Hotel;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.HotelsRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;
import nure.kaplun.HotelSupervisor.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Anton on 24.05.2017.
 */
@WebServlet(name = "HotelEmployeesGetterServlet")
public class HotelEmployeesGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hotelId = Integer.parseInt(request.getParameter("id"));
        Connection dbConnection = DataBaseConnector.openConnection();
        EmployeesRepository employeesRepository = new EmployeesRepository(dbConnection);
        List<Employee> employeesList = employeesRepository.getEmployeesByHotel(hotelId);
        DataBaseConnector.closeConnection(dbConnection);
        Employee[] employeesArray = employeesList.toArray(new Employee[employeesList.size()]);
        JsonSender.sendJson(response, employeesArray);
    }
}
