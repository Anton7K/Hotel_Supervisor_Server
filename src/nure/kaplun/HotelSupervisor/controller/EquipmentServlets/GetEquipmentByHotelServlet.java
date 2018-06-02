package nure.kaplun.HotelSupervisor.controller.EquipmentServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.*;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 25.05.2017.
 */
@WebServlet(name = "GetEquipmentByHotelServlet")
public class GetEquipmentByHotelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int hotelId = Integer.parseInt(request.getParameter("hotelId"));
        List<RoomEquipment> resultList = new ArrayList<>();
        Connection connection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository = new RoomsRepository(connection);
        List<Room> rooms = roomsRepository.getRoomsByHotel(hotelId);
        EquipmentRepository equipmentRepository = new EquipmentRepository(connection);
        List<Equipment> allEquipmentList = new ArrayList<>();
        for(Room room: rooms){
            List<Equipment> roomEquipmentList = equipmentRepository.getEquipmentByRoom(room.getId());
            allEquipmentList.addAll(roomEquipmentList);
        }
        for(Equipment appliance: allEquipmentList){
            RoomEquipment roomEquipment = new RoomEquipment(appliance);
            resultList.add(roomEquipment);
        }
        DataBaseConnector.closeConnection(connection);
        RoomEquipment[] result = resultList.toArray(new RoomEquipment[resultList.size()]);
        JsonSender.sendJson(response, result);
    }
}
