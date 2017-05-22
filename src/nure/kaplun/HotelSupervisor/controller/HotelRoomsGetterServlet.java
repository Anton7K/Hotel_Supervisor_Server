package nure.kaplun.HotelSupervisor.controller;

import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.Hotel;
import nure.kaplun.HotelSupervisor.model.Room;

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
@WebServlet(name = "HotelRoomsGetterServlet")
public class HotelRoomsGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hotelId = Integer.parseInt(request.getParameter("id"));
        List<Room> roomsList = DataBaseManager.getRoomsByHotel(DataBaseConnector.openConnection(), hotelId);
        Room[] roomsArray = roomsList.toArray(new Room[roomsList.size()]);
        JsonSender.sendJson(response, roomsArray);
    }
}
