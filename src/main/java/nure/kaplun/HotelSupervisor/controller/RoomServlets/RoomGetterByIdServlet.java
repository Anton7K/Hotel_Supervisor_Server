package nure.kaplun.HotelSupervisor.controller.RoomServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Equipment;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;
import nure.kaplun.HotelSupervisor.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * Created by Anton on 05.06.2018.
 */
@WebServlet(name = "RoomGetterByIdServlet",  urlPatterns={"/getRoomById"})
public class RoomGetterByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        Connection connection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository = new RoomsRepository(connection);
        Room room = roomsRepository.getRoomById(roomId);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(response, room);
    }
}
