package nure.kaplun.HotelSupervisor.model;

import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;

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

    private static final String INSERT_ADMINISTRATOR_QUERY = "INSERT INTO hotel_administrators (name,login,password) VALUES (?,?,?)";
    private static final String INSERT_HOTEL_QUERY = "INSERT INTO hotels(name, adminId)VALUES(?,?)";
    private static final String SELECT_ALL_ADMINS_QUERY = "SELECT * FROM hotel_administrators";

    private static final String SELECT_ADMIN_BY_LOGIN = "SELECT * FROM hotel_administrators WHERE login=?";
    private static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM "+ DbTables.EMPLOYEES_TABLE+" WHERE login=?";
    private static final String SELECT_HOTELS_BY_ADMIN = "SELECT * FROM "+ DbTables.HOTELS_TABLE +" WHERE adminId=?";

    private static final String DELETE_HOTEL_BY_ID = "DELETE FROM "+ DbTables.HOTELS_TABLE+" WHERE id=?";

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
        deleteEntity(connection, hotelId, DELETE_HOTEL_BY_ID);
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
