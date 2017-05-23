package nure.kaplun.HotelSupervisor.controller.HotelServlets;

import nure.kaplun.HotelSupervisor.controller.JsonSender;
import nure.kaplun.HotelSupervisor.model.DataBaseConnector;
import nure.kaplun.HotelSupervisor.model.Hotel;
import nure.kaplun.HotelSupervisor.model.Repositories.HotelsRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Anton on 21.05.2017.
 */
@WebServlet(name = "HotelsGetterServlet")
public class HotelsGetterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = (int) session.getAttribute("id");
        Connection connection = DataBaseConnector.openConnection();
        HotelsRepository hotelsRepository = new HotelsRepository(connection);
        List<Hotel> hotelsList = hotelsRepository.getHotelsByAdmin(adminId);
        DataBaseConnector.closeConnection(connection);
        Hotel[] hotels = hotelsList.toArray(new Hotel[hotelsList.size()]);
        JsonSender.sendJson(response, hotels);
    }
}
