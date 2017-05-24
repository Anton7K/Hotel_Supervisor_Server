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

    private static final String SELECT_EMPLOYEE_HOTEL_Id = "SELECT hotelId FROM "+ DbTables.EMPLOYEES_TABLE +" WHERE id=?";

    public static int getEmployeeHotelId(Connection connection, int employeeId){
        int hotelId=-1;
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_EMPLOYEE_HOTEL_Id)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                hotelId=rs.getInt("hotelId");
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return hotelId;
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


}
