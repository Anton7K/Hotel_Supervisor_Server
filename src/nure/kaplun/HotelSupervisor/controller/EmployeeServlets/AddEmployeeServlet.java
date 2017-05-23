package nure.kaplun.HotelSupervisor.controller.EmployeeServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.controller.UserDataChecker;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Employee;
import nure.kaplun.HotelSupervisor.model.Repositories.AdminsRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;

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
 * Created by Anton on 24.05.2017.
 */
@WebServlet(name = "AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        int age = Integer.parseInt(request.getParameter("age"));
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        UserDataChecker checker = new UserDataChecker();
        Map<String, String> jsonMap = new LinkedHashMap<>();
        try {
            if(!checker.isUserWithLoginExist(login, "employee")){
                Employee employee = new Employee(name, login, password, age, hotelId);
                Connection connection = DataBaseConnector.openConnection();
                EmployeesRepository employeesRepository = new EmployeesRepository(connection);
                employeesRepository.addEmployee(employee);
                DataBaseConnector.closeConnection(connection);
                jsonMap.put("isRegistered", "true");
            }
            else {
                jsonMap.put("errors", "userExist");
            }
        } catch (IncorrectUserRoleException e) {
            e.printStackTrace();
        }
        JsonSender.sendJson(response, jsonMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
