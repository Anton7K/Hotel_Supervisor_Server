package nure.kaplun.HotelSupervisor.controller;

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
 * Created by Anton on 17.05.2017.
 */
@WebServlet(name = "SessionCheckerServlet")
public class SessionCheckerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isValid=false;
        Map<String, Object> jsonMap = new LinkedHashMap<>();
//        boolean isValid = request.isRequestedSessionIdValid();
        HttpSession session =request.getSession(false);
        if(session!=null){
            isValid=true;
            jsonMap.put("name", session.getAttribute("name"));
            jsonMap.put("role", session.getAttribute("role"));
        }

        jsonMap.put("isValid", isValid);
        JsonSender.sendJson(response, jsonMap);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
