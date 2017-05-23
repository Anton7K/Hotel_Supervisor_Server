package nure.kaplun.HotelSupervisor.model.Repositories;

import nure.kaplun.HotelSupervisor.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Anton on 23.05.2017.
 */
public class AdminsRepository {

    public static final String INSERT_ADMINISTRATOR_QUERY = "INSERT INTO hotel_administrators (name,login,password) VALUES (?,?,?)";
    public static final String SELECT_ADMIN_BY_LOGIN = "SELECT * FROM hotel_administrators WHERE login=?";
    private Connection connection;

    public AdminsRepository(Connection connection) {
        this.connection = connection;
    }

    public void addAdmin(Admin admin){
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_ADMINISTRATOR_QUERY)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getLogin());
            stmt.setString(3, admin.getPassword());
            stmt.execute();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public Admin getAdminByLogin(String login){
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
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        return admin;
    }
}
