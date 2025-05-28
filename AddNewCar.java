package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Database;
import Model.Operation;
import Model.User;

public class AddNewCar implements Operation {
	
	@Override
	public void operation(Database database, Scanner s, User user) {
		
		System.out.println("Enter brand:");
		String brand = s.next();
		System.out.println("Enter model:");
		String model = s.next();
		System.out.println("Enter color:");
		String color = s.next();
		System.out.println("Enter year (int):");
		int year = s.nextInt();
		System.out.println("Enter price per hour (double):");
		double price = s.nextDouble();
		int available = 0;
		
		try {
            ResultSet rs = database.getStatement().executeQuery("SELECT MAX(ID) AS maxID FROM cars");
            rs.next();
            int ID = rs.getInt("maxID") + 1;
            if (rs.wasNull()) {
                ID = 0; // If no rows exist yet
            }
			
			
			String insert = "INSERT INTO `cars`(`ID`, `Brand`, `Model`, `Color`, `Year`, `Price`, `Available`) VALUES (" +
		               ID + ", '" + brand + "', '" + model + "', '" + color + "', " + year + ", " + price + ", " + available + ");";


			
			database.getStatement().execute(insert);
			System.out.println("Car added successfully");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
