package nure.kaplun.HotelSupervisor.controller.RoomServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Repositories.HotelsRepository;
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
@WebServlet(name = "DeleteHotelServlet",  urlPatterns={"/deleteRoom"})
public class DeleteRoomServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roomId = Integer.parseInt(req.getParameter("roomId"));
        Connection connection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository = new RoomsRepository(connection);
        boolean isRoomDeleted = roomsRepository.deleteRoomById(roomId);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(resp, isRoomDeleted);
    }
}
