package nure.kaplun.HotelSupervisor;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.controller.UserDataChecker;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.Equipment;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;

import java.sql.Connection;

/**
 * Created by Anton on 15.05.2017.
 */
public class Test {
    public static void main(String[] args){
        int equipmentId = 1;
        int curValue = 10;
        String operation = "minus";
        Connection connection = DataBaseConnector.openConnection();
        EquipmentRepository equipmentRepository = new EquipmentRepository(connection);
        if(operation=="plus"){
            equipmentRepository.updateEquipmentValue(equipmentId, curValue);
        }
        if(operation=="minus"){
            equipmentRepository.updateEquipmentValue(equipmentId, -curValue);
        }
        DataBaseConnector.closeConnection(connection);
    }
}
