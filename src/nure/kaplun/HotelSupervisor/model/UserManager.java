package nure.kaplun.HotelSupervisor.model;

import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Repositories.AdminsRepository;
import nure.kaplun.HotelSupervisor.model.Repositories.EmployeesRepository;

import java.sql.Connection;

/**
 * Created by Anton on 18.05.2017.
 */
public class UserManager {
    public static User getUserByLogin(String login, String role) throws IncorrectUserRoleException {
        User user = null;
        if(role.equals("admin")){
            Connection connection = DataBaseConnector.openConnection();
            AdminsRepository adminsRepository = new AdminsRepository(connection);
            user = adminsRepository.getAdminByLogin(login);
            DataBaseConnector.closeConnection(connection);
        }
        else if(role.equals("employee")){
            Connection connection = DataBaseConnector.openConnection();
            EmployeesRepository employeesRepository = new EmployeesRepository(connection);
            user = employeesRepository.getEmployeeByLogin(login);
            DataBaseConnector.closeConnection(connection);
        }
        else{
            throw new IncorrectUserRoleException("Incorrect role()"+role+"!");
        }
        return user;
    }
}
