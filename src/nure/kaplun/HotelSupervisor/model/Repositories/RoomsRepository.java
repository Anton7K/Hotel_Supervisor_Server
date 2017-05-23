package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.DbTables;
import nure.kaplun.HotelSupervisor.model.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 22.05.2017.
 */
public class RoomsRepository {
    private static final String INSERT_ROOM_QUERY = "INSERT INTO" + DbTables.ROOMS_TABLE + "(name, hotelId)VALUES(?,?)";
    private static final String SELECT_ROOMS_BY_HOTEL = "SELECT * FROM "+ DbTables.ROOMS_TABLE +" WHERE hotelId=?";
    private static final String DELETE_ROOM_BY_ID = "DELETE FROM "+ DbTables.ROOMS_TABLE+" WHERE id=?";

    private Connection connection;

    public RoomsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Room> getRoomsByHotel(int hotelId){
        List<Room> rooms = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ROOMS_BY_HOTEL)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Room room = new Room(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("hotelId"));
                rooms.add(room);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return rooms;
    }
    public void addRoom(String name, int hotelId){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_ROOM_QUERY)) {
            stmt.setString(1, name);
            stmt.setInt(1, hotelId);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public  void deleteRoomById(int roomId){
        DataBaseManager.deleteEntity(connection, roomId, DELETE_ROOM_BY_ID);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


}
