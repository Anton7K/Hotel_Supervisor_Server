package nure.kaplun.HotelSupervisor.controller;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Anton on 21.05.2017.
 */
public class JsonSender {

    public static void sendJson(HttpServletResponse response, Object toWrite) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(toWrite);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
