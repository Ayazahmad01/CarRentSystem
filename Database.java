package Model;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3307/carrentalsystem";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "#1#2#3%1%2%3"; // Or use URL-encoded: "%231%232%233%251%252%253"
    
    

    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            // Add SSL and other connection parameters
            String connectionUrl = DB_URL + "?useSSL=false&allowPublicKeyRetrieval=true";
            
            // Establish connection
            connection = DriverManager.getConnection(connectionUrl, DB_USER, DB_PASSWORD);
            
            // Create reusable statement
            statement = connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
            );
            
            System.out.println("Database connection established successfully!");
            
        } catch (SQLException e) {
            System.err.println("Failed to connect to database:");
            System.err.println("URL: " + DB_URL);
            System.err.println("User: " + DB_USER);
            System.err.println("Possible solutions:");
            System.err.println("1. Verify MySQL is running on port 3307");
            System.err.println("2. Check username/password in MySQL");
            System.err.println("3. Ensure user has privileges on 'carrentalsystem'");
            e.printStackTrace();
            
            // Ensure resources are null if connection fails
            statement = null;
            connection = null;
        }
    }

    // Add proper resource cleanup
    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println("Error closing database resources:");
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
}



