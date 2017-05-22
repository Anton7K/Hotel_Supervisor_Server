package nure.kaplun.HotelSupervisor.model;

/**
 * Created by Anton on 21.05.2017.
 */
public class Room {
    private int id;
    private String name;
    private int hotelId;

    public Room(int id, String name, int hotelId) {
        this.id = id;
        this.name = name;
        this.hotelId = hotelId;
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

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}
