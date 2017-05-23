package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DbTables;
import nure.kaplun.HotelSupervisor.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 23.05.2017.
 */
public class EmployeesRepository {

    public static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM "+ DbTables.EMPLOYEES_TABLE+" WHERE login=?";
    public static final String SELECT_EMPLOYEES_BY_HOTEL = "SELECT * FROM "+ DbTables.EMPLOYEES_TABLE+" WHERE hotelId=?";
    public static final String INSERT_EMPLOYEE_QUERY = "INSERT INTO " + DbTables.EMPLOYEES_TABLE + " (name,login,password,age,hotelId) VALUES (?,?,?,?,?)";
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

    public List<Employee> getEmployeesByHotel(int hotelId){
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_EMPLOYEES_BY_HOTEL)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Employee employee = new Employee(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getInt("age"),
                        rs.getInt("hotelId"));
                employees.add(employee);
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return employees;
    }

    public void addEmployee(Employee employee){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_EMPLOYEE_QUERY)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getLogin());
            stmt.setString(3, employee.getPassword());
            stmt.setInt(4, employee.getAge());
            stmt.setInt(5, employee.getHotelId());
            stmt.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
