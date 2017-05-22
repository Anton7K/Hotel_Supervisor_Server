package nure.kaplun.HotelSupervisor.model;

/**
 * Created by Anton on 21.05.2017.
 */
public class Hotel {
    private int id;
    private String name;
    private int adminId;

    public Hotel(int id, String name, int adminId) {
        this.id = id;
        this.name = name;
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
