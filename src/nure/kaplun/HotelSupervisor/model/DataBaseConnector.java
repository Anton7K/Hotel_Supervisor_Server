package nure.kaplun.HotelSupervisor.model;

import java.sql.*;

/**
 * Created by Anton on 14.05.2017.
 */
public class DataBaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/hotel_supervisor";
    private static final String USER = "hotel";
    private static final String PASSWORD = "password";

    private Connection connection;

    private static DataBaseConnector instance;

    public synchronized static DataBaseConnector getInstance() {
        if (instance == null) {
            instance = new DataBaseConnector();
        }
        return instance;
    }

    private DataBaseConnector() {

    }

    public Connection getConnection() {

        if (connection == null) {
            return openConnection();
        } else {
            boolean isConnectionClosed = true;
            try {
                isConnectionClosed = connection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (isConnectionClosed) {
                return openConnection();
            }
            return connection;
        }
    }

    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
//            this.connection = connection;
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }


}
