package nure.kaplun.HotelSupervisor.controller.HotelServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Anton on 22.05.2017.
 */
@WebServlet(name = "AddHotelServlet")
public class AddHotelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hotelName = request.getParameter("hotelName");
        HttpSession session = request.getSession();
        int adminId =  (int)session.getAttribute("id");
        DataBaseManager.addHotel(DataBaseConnector.openConnection(), hotelName, adminId);
        JsonSender.sendJson(response,true);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
