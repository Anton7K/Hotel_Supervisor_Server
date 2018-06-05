package nure.kaplun.HotelSupervisor.controller.EmployeeServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.controller.UserDataChecker;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Employee;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anton on 05.06.2018.
 */
@WebServlet(name = "EditEmployeeServlet",  urlPatterns={"/editEmployee"})
public class EditEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String name = request.getParameter("name");
        String login = request.getParameter("login");
//        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        Connection connection = DataBaseConnector.openConnection();
        EmployeesRepository employeesRepository = new EmployeesRepository(connection);
        Employee previousEmployee = employeesRepository.getEmployeeById(employeeId);
        String previousLogin = previousEmployee.getLogin();
        UserDataChecker checker = new UserDataChecker();
        Map<String, String> jsonMap = new LinkedHashMap<>();
        try {
            if(previousLogin.equals(login) || !checker.isUserWithLoginExist(login, "employee")){
                Employee employee = new Employee(employeeId, name, login, age, hotelId);
                connection = DataBaseConnector.openConnection();
                employeesRepository.updateEmployeeWithoutPassword(employee);
                //DataBaseConnector.closeConnection(connection);
                jsonMap.put("isUpdated", "true");
            }
            else {
                jsonMap.put("errors", "userExist");
            }
        } catch (IncorrectUserRoleException e) {
            e.printStackTrace();
        }
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(response, jsonMap);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
