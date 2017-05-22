package nure.kaplun.HotelSupervisor.controller;

import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.User;
import nure.kaplun.HotelSupervisor.model.UserManager;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Anton on 15.05.2017.
 */
public class UserDataChecker {
    private String error;

    public String getError() {
        return error;
    }

    public  boolean isUserWithLoginExist(String login, String role)throws IncorrectUserRoleException{
        if(UserManager.getUserByLogin(login,role)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isPasswordCorrect(String enteredPassword, String realPassword){
        String encryptedPassword = null;
        try {
            encryptedPassword = Encryption.encryptPassword(enteredPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if(encryptedPassword.equals(realPassword)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkUserData(String login, String password, String role)throws IncorrectUserRoleException{
        User user = UserManager.getUserByLogin(login, role);

        if(user!=null){
            if(isPasswordCorrect(password, user.getPassword())){
                return true;
            } else{
                error="password";
            }
        }else {
            error="login";
        }
        return false;
    }

}
