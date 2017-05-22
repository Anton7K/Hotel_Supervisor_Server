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
    private static final String SELECT_EQUIPMENT_BY_ROOM = "SELECT * FROM "+ EQUIPMENT_TABLE +" WHERE roomId=?";

    private static final String DELETE_HOTEL_BY_ID = "DELETE FROM "+HOTELS_TABLE+" WHERE id=?";
    private static final String DELETE_ROOM_BY_ID = "DELETE FROM "+ROOMS_TABLE+" WHERE id=?";
    private static final String DELETE_EQUIPMENT_BY_ID = "DELETE FROM "+EQUIPMENT_TABLE+" WHERE id=?";

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

    public static List<Equipment> getEquipmentByRoom(Connection connection, int roomId){
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
            connection.close();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return equipment;
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
//==================Delete===================

    private static void deleteEntity(Connection connection, int hotelId, String deleteHotelById) {
        try (PreparedStatement stmt = connection.prepareStatement(deleteHotelById)) {
            stmt.setInt(1, hotelId);
            stmt.executeQuery();
            stmt.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void deleteHotelById(Connection connection, int hotelId){
        deleteEntity(connection, hotelId, DELETE_HOTEL_BY_ID);
    }

    public static void deleteRoomById(Connection connection, int roomId){
        deleteEntity(connection, roomId, DELETE_ROOM_BY_ID);
    }

    public static void deleteEquipmentById(Connection connection, int equipmentId){
        deleteEntity(connection, equipmentId, DELETE_EQUIPMENT_BY_ID);
    }
//    public static void deleteRoomWithReferences(int roomId){
//        List<Equipment> roomEquipment = getEquipmentByRoom()
//    }

//    public static void deleteHotelWithReferences(int hotelId){
//        List<Room> ho
//    }

//==================INSERT===================

    public static void addHotel(Connection connection, String name, int adminId){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_HOTEL_QUERY)) {
            stmt.setString(1, name);
            stmt.setInt(2, adminId);
            stmt.execute();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
