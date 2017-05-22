package nure.kaplun.HotelSupervisor.exceptions;

import java.io.IOException;

/**
 * Created by Anton on 15.05.2017.
 */
public class IncorrectUserRoleException extends Exception {
    public IncorrectUserRoleException(String message) {
        super(message);
    }
}
