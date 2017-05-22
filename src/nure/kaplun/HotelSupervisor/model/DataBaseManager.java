package nure.kaplun.HotelSupervisor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 14.05.2017.
 */
public class DataBaseManager {
    private static final String ADMINS_TABLE = "hotel_administrators";
    private static final String HOTELS_TABLE = "hotels";
    private static final String ROOMS_TABLE = "rooms";
    private static final String EQUIPMENT_TABLE = "equipment";
    private static final String EMPLOYEES_TABLE = "employees";

    private static final String INSERT_ADMINISTRATOR_QUERY = "INSERT INTO hotel_administrators (name,login,password) VALUES (?,?,?)";
    private static final String INSERT_HOTEL_QUERY = "INSERT INTO hotels(name, adminId)VALUES(?,?)";
    private static final String INSERT_ROOM_QUERY = "INSERT INTO rooms (hotelId)VALUES(?)";
    private static final String SELECT_ALL_ADMINS_QUERY = "SELECT * FROM hotel_administrators";

    private static final String SELECT_ADMIN_BY_LOGIN = "SELECT * FROM hotel_administrators WHERE login=?";
    private static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM "+EMPLOYEES_TABLE+" WHERE login=?";
    private static final String SELECT_HOTELS_BY_ADMIN = "SELECT * FROM "+ HOTELS_TABLE +" WHERE adminId=?";
    private static final String SELECT_ROOMS_BY_HOTEL = "SELECT * FROM "+ ROOMS_TABLE +" WHERE hotelId=?";

    public static void addAdmin(Connection connection, Admin admin){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_ADMINISTRATOR_QUERY)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getLogin());
            stmt.setString(3, admin.getPassword());
            stmt.execute();
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public static Admin getAdminByLogin(Connection connection, String login){
        Admin admin=null;
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_ADMIN_BY_LOGIN)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                admin= new Admin(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"));
            }
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return admin;
    }

    public static List<Hotel> getHotelsByAdmin(Connection connection, int adminId){
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
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotels;
    }

    public static List<Room> getRoomsByHotel(Connection connection, int hotelId){
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
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return rooms;
    }


    public static Employee getEmployeeByLogin(Connection connection, String login){
        Employee employee = null;
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_EMPLOYEE_BY_LOGIN)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getInt("hotelId"));
            }
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return employee;
    }


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
