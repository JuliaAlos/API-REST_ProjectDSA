package edu.upc.dsa;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FactorySession {


    public static Connection getConnection() {
        Connection conn = null;
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "Mazinger72");
        try {
            conn =
                    DriverManager.getConnection("jdbc:mariadb://localhost:3306/insignia", properties);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return conn;
    }
}
