package nure.kaplun.HotelSupervisor.model;

/**
 * Created by Anton on 15.05.2017.
 */
public class Employee extends User {
    private int age;
    private int hotelId;

    public Employee(int id, String name, String login, String password, int age, int hotelId) {
        super(id, name, login, password);
        this.age = age;
        this.hotelId = hotelId;
    }
}
