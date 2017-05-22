package nure.kaplun.HotelSupervisor.model;

/**
 * Created by Anton on 15.05.2017.
 */
public class Admin extends User{
    public Admin(int id, String name, String login, String password) {
        super(id, name, login, password);
    }

    public Admin() {
    }

    public Admin(String name, String login, String password) {
        super(name, login, password);
    }
}
