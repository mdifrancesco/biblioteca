package it.evolvere.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {


    public static final String URL = "jdbc:postgresql://127.0.0.1:5432/biblioteca";
    public static final String USER = "postgres";
    public static final String PASS = "agile";

    private static Connection connection;

    public static synchronized Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
        }

        if(connection != null){
            return  connection;
        }

        try {
            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }
}
