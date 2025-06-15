	package Model;
	
	import java.sql.*;
	
	public class Database {
		private static final String DB_URL = "jdbc:mysql://localhost:3307/carrentalsystem?useSSL=false&allowPublicKeyRetrieval=true";
		private static final String DB_USER = "root";
		private static final String DB_PASSWORD = "your_strong_password";

	
	    private Connection connection;
	    private Statement statement;
	
	    public Database() {
	        try {
	            // Optional: explicitly load the MySQL driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	
	            // Establish connection using class-level fields
	            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	
	            // Create reusable statement
	            statement = connection.createStatement(
	                ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY
	            );
	
	            System.out.println("✅ Database connection established successfully!");
	
	        } catch (ClassNotFoundException e) {
	            System.err.println("MySQL JDBC Driver not found. Make sure the JAR is added.");
	            e.printStackTrace();
	
	        } catch (SQLException e) {
	            System.err.println("❌ Failed to connect to database:");
	            System.err.println("URL: " + DB_URL);
	            System.err.println("User: " + DB_USER);
	            System.err.println("Possible solutions:");
	            System.err.println("1. Verify MySQL is running on port 3306");
	            System.err.println("2. Check username/password in MySQL");
	            System.err.println("3. Ensure user has privileges on 'carrentalsystem'");
	            e.printStackTrace();
	
	            statement = null;
	            connection = null;
	        }
	    }
	
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
	
	
	
