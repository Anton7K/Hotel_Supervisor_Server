package nure.kaplun.HotelSupervisor.controller.EquipmentServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Equipment;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Anton on 23.05.2017.
 */
@WebServlet(name = "AddEquipmentServlet")
public class AddEquipmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int maxValue = Integer.parseInt(request.getParameter("maxValue"));
        Connection connection = DataBaseConnector.openConnection();
        EquipmentRepository equipmentRepository = new EquipmentRepository(connection);
        equipmentRepository.addEquipment(type, roomId, maxValue);
        DataBaseConnector.closeConnection(connection);
        JsonSender.sendJson(response,true);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
