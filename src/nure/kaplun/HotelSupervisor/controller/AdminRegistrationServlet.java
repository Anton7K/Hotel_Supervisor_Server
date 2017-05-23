package nure.kaplun.HotelSupervisor.controller;

import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Repositories.AdminsRepository;

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
 * Created by Anton on 15.05.2017.
 */
@WebServlet(name = "AdminRegistrationServlet")
public class AdminRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDataChecker checker = new UserDataChecker();
        Map<String, String> jsonMap = new LinkedHashMap<>();
        try {
            if(!checker.isUserWithLoginExist(login, "admin")){
                Admin admin = new Admin(name,login,password);
                Connection connection = DataBaseConnector.openConnection();
                AdminsRepository adminsRepository = new AdminsRepository(connection);
                adminsRepository.addAdmin(admin);
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
        doPost(request,response);
    }
}
