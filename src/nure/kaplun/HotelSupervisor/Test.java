package nure.kaplun.HotelSupervisor;

import nure.kaplun.HotelSupervisor.controller.UserDataChecker;
import nure.kaplun.HotelSupervisor.exceptions.IncorrectUserRoleException;
import nure.kaplun.HotelSupervisor.model.Admin;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.DataBaseManager;
import nure.kaplun.HotelSupervisor.model.Repositories.EquipmentRepository;

import java.sql.Connection;

/**
 * Created by Anton on 15.05.2017.
 */
public class Test {
    public static void main(String[] args){
        Connection connection = DataBaseConnector.openConnection();
        EquipmentRepository equipmentRepository = new EquipmentRepository(connection);
        equipmentRepository.addEquipment("Мыло", 1, 500);
        DataBaseConnector.closeConnection(connection);
    }
}
