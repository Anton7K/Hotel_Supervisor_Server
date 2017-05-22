package nure.kaplun.HotelSupervisor.model;

/**
 * Created by Anton on 22.05.2017.
 */
public class Equipment {
    private int id;
    private String type;
    private int roomId;
    private int maxValue;
    private int currentValue;

    public Equipment(int id, String type, int roomId, int maxValue, int currentValue) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.maxValue = maxValue;
        this.currentValue = currentValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
