package nure.kaplun.HotelSupervisor.model;

import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;

/**
 * Created by Anton on 18.05.2017.
 */
public class UserManager {
    public static User getUserByLogin(String login, String role) throws IncorrectUserRoleException {
        User user = null;
        if(role.equals("admin")){
            user = DataBaseManager.getAdminByLogin(DataBaseConnector.openConnection(), login);
        }
        else if(role.equals("employee")){
            user = DataBaseManager.getEmployeeByLogin(DataBaseConnector.openConnection(), login);
        }
        else{
            throw new IncorrectUserRoleException("Incorrect role()"+role+"!");
        }
        return user;
    }
}
