package nure.kaplun.HotelSupervisor.controller;

import com.google.gson.Gson;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.User;
import nure.kaplun.HotelSupervisor.model.UserManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Anton on 15.05.2017.
 */
@WebServlet(name = "AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String role = request.getParameter("user_role");

        UserDataChecker checker=new UserDataChecker();
        try {
            boolean isAuthorized = false;
            boolean isUserValid = checker.checkUserData(login, password, role);
            if(isUserValid){
                User user = UserManager.getUserByLogin(login, role);
                HttpSession session = request.getSession(true);
                session.setAttribute("name", user.getName());
                session.setAttribute("role", role);
                session.setAttribute("id", user.getId());
                isAuthorized=true;
            }else {

            }
            Map<String, Object> jsonMap = new LinkedHashMap<>();
            jsonMap.put("isAuthorized", isAuthorized);
            jsonMap.put("error", checker.getError());
            JsonSender.sendJson(response, jsonMap);

//            response.addHeader("Access-Control-Allow-Origin", "http://localhost:63343");
//            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//            response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//            response.addHeader("Access-Control-Max-Age", "1728000");

        } catch (IncorrectUserRoleException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
