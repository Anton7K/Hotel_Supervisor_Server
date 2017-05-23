package nure.kaplun.HotelSupervisor.controller.RoomServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;
import nure.kaplun.HotelSupervisor.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
        Connection dbConnection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository = new RoomsRepository(dbConnection);
        List<Room> roomsList = roomsRepository.getRoomsByHotel(hotelId);
        try {
            dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Room[] roomsArray = roomsList.toArray(new Room[roomsList.size()]);
        JsonSender.sendJson(response, roomsArray);
    }
}
