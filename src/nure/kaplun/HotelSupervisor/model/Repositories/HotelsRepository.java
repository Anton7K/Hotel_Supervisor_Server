package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.DbTables;
import nure.kaplun.HotelSupervisor.model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 23.05.2017.
 */
public class HotelsRepository {
    public static final String SELECT_HOTELS_BY_ADMIN = "SELECT * FROM "+ DbTables.HOTELS_TABLE +" WHERE adminId=?";
    public static final String SELECT_ALL_HOTELS= "SELECT * FROM "+ DbTables.HOTELS_TABLE;
    public static final String DELETE_HOTEL_BY_ID = "DELETE FROM "+ DbTables.HOTELS_TABLE+" WHERE id=?";
    public static final String INSERT_HOTEL_QUERY = "INSERT INTO hotels(name, adminId)VALUES(?,?)";
    private Connection connection;

    public HotelsRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Hotel> getHotelsByAdmin(int adminId){
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_HOTELS_BY_ADMIN)) {
            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Hotel hotel = new Hotel(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("adminId"));
                hotels.add(hotel);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotels;
    }

    public List<Hotel> getAllHotels(){
        List<Hotel> hotels = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_HOTELS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Hotel hotel = new Hotel(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("adminId"));
                hotels.add(hotel);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotels;
    }
    public void addHotel(String name, int adminId){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_HOTEL_QUERY)) {
            stmt.setString(1, name);
            stmt.setInt(2, adminId);
            stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }


}
