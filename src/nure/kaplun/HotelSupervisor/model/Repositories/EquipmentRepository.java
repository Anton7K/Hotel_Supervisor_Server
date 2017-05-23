package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.DbTables;
import nure.kaplun.HotelSupervisor.model.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 23.05.2017.
 */
public class EquipmentRepository {
    public static final String SELECT_EQUIPMENT_BY_ROOM = "SELECT * FROM "+ DbTables.EQUIPMENT_TABLE +" WHERE roomId=?";
    public static final String DELETE_EQUIPMENT_BY_ID = "DELETE FROM "+ DbTables.EQUIPMENT_TABLE+" WHERE id=?";
    private static final String INSERT_EQUIPMENT_QUERY = "INSERT INTO " + DbTables.EQUIPMENT_TABLE + " (type,roomId,`maxValue`,currentValue) VALUES (?,?,?,?)";

    private Connection connection;

    public EquipmentRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Equipment> getEquipmentByRoom(int roomId){
        List<Equipment> equipment = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_EQUIPMENT_BY_ROOM)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Equipment appliance = new Equipment(rs.getInt("id"),
                        rs.getString("type"),
                        rs.getInt("roomId"),
                        rs.getInt("maxValue"),
                        rs.getInt("currentValue"));
                equipment.add(appliance);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return equipment;
    }

    public  void deleteEquipmentById(int roomId){
        DataBaseManager.deleteEntity(connection, roomId, DELETE_EQUIPMENT_BY_ID);
    }

    public void addEquipment(String type, int roomId, int maxValue){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_EQUIPMENT_QUERY)) {
            stmt.setString(1, type);
            stmt.setInt(2, roomId);
            stmt.setInt(3, maxValue);
            stmt.setInt(4, 0);
            stmt.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
