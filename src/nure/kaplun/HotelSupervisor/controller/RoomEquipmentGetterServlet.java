package nure.kaplun.HotelSupervisor.controller;

import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.Equipment;
import nure.kaplun.HotelSupervisor.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Anton on 22.05.2017.
 */
@WebServlet(name = "RoomEquipmentGetterServlet")
public class RoomEquipmentGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        List<Equipment> equipmentList = DataBaseManager.getEquipmentByRoom(DataBaseConnector.openConnection(), roomId);
        Equipment[] equipmentArray = equipmentList.toArray(new Equipment[equipmentList.size()]);
        JsonSender.sendJson(response, equipmentArray);
    }
}
