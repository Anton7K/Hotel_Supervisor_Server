package nure.kaplun.HotelSupervisor;

import nure.kaplun.HotelSupervisor.controller.UserDataChecker;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;

/**
 * Created by Anton on 15.05.2017.
 */
public class Test {
    public static void main(String[] args){
        UserDataChecker checker=new UserDataChecker();
        try {
            boolean isValid = checker.checkUserData("dd","12345", "admin");
            System.out.println(isValid);
            System.out.println(checker.getError());
        } catch (IncorrectUserRoleException e) {
            e.printStackTrace();
        }

//        String name = "Petya";
//        String login = "dd";
//        String password = "12345";
//        UserDataChecker checker = new UserDataChecker();
//        try {
//            if(!checker.isUserWithLoginExist(login, "admin")){
//                Admin admin = new Admin(name,login,password);
//                DataBaseManager.addAdmin(DataBaseConnector.openConnection(),admin);
//            }
//        } catch (IncorrectUserRoleException e) {
//            e.printStackTrace();
//        }
    }
}
