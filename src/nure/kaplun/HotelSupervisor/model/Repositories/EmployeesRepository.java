package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.DbTables;
import nure.kaplun.HotelSupervisor.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anton on 23.05.2017.
 */
public class EmployeesRepository {

    public static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM "+ DbTables.EMPLOYEES_TABLE+" WHERE login=?";
    private Connection connection;

    public EmployeesRepository(Connection connection) {
        this.connection = connection;
    }

    public Employee getEmployeeByLogin(String login){
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
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return employee;
    }
}
