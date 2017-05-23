package nure.kaplun.HotelSupervisor.model;

import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.HotelsRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anton on 14.05.2017.
 */
public class DataBaseManager {

    //==================Delete===================

    public static void deleteEntity(Connection connection, int entityId, String deleteEntityByIdQuery) {
        try (PreparedStatement stmt = connection.prepareStatement(deleteEntityByIdQuery)) {
            stmt.setInt(1, entityId);
            stmt.execute();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteHotelById(Connection connection, int hotelId){
        deleteEntity(connection, hotelId, HotelsRepository.DELETE_HOTEL_BY_ID);
    }

    public static void deleteEquipmentById(Connection connection, int equipmentId){
        deleteEntity(connection, equipmentId, EquipmentRepository.DELETE_EQUIPMENT_BY_ID);
    }
//    public static void deleteRoomWithReferences(int roomId){
//        List<Equipment> roomEquipment = getEquipmentByRoom()
//    }

//    public static void deleteHotelWithReferences(int hotelId){
//        List<Room> ho
//    }

//==================INSERT===================

    public static void main(String[] args) {
        try {
            Connection connection = DataBaseConnector.openConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM hotel_administrators WHERE login=?");
            stmt.setString(1, "lll");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
