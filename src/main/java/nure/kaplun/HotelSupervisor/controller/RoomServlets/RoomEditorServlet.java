package nure.kaplun.HotelSupervisor.controller.RoomServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;

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
@WebServlet(name = "RoomEditorServlet",  urlPatterns={"/editRoom"})
public class RoomEditorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String roomName = request.getParameter("roomName");
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        Connection connection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository= new RoomsRepository(connection);
        roomsRepository.updateRoom(roomId, roomName, hotelId);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(response,true);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
