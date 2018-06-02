package nure.kaplun.HotelSupervisor.model;

import nure.kaplun.HotelSupervisor.model.Repositories.RoomsRepository;

import java.sql.Connection;

/**
 * Created by Anton on 24.05.2017.
 */
public class RoomEquipment {
    private int id;
    private String type;
    private String roomName;
    private int maxValue;
    private int currentValue;

    public RoomEquipment(Equipment equipment){
        id=equipment.getId();
        type=equipment.getType();
        maxValue=equipment.getMaxValue();
        currentValue=equipment.getCurrentValue();
        Connection connection = DataBaseConnector.openConnection();
        RoomsRepository roomsRepository = new RoomsRepository(connection);
        Room equipmentRoom = roomsRepository.getRoomById(equipment.getRoomId());
        DataBaseConnector.closeConnection(connection);
        roomName = equipmentRoom.getName();
    }
}
