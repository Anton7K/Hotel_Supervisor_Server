package nure.kaplun.HotelSupervisor.controller;

import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.Hotel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Anton on 21.05.2017.
 */
@WebServlet(name = "HotelsGetterServlet")
public class HotelsGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("id");
        List<Hotel> hotelsList = DataBaseManager.getHotelsByAdmin(DataBaseConnector.openConnection(), adminId);
        Hotel[] hotels = hotelsList.toArray(new Hotel[hotelsList.size()]);
        JsonSender.sendJson(response, hotels);
    }
}
