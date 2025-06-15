package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/carrentalsystem?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "root";
        String password = "ayazahmad110.0";

        System.out.println("Trying to connect to database...");

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            if (connection != null) {
                System.out.println("✅ Connection successful!");
            } else {
                System.out.println("❌ Connection failed!");
            }
        } catch (SQLException e) {
            System.err.println("❌ Failed to connect to database:");
            e.printStackTrace();
        }
    }
}

