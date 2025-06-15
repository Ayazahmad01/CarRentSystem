package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

public class RentCar implements Operation {

	@Override
	public void operation(Database database, Scanner s, User user) {
		
		System.out.println("Enter car ID (int): (-1 to show all cars)");
		int carID = s.nextInt();
		while (carID==-1) {
			new ViewCars().operation(database, s, user);
			System.out.println("Enter car ID (int): (-1 to show all cars)");
			carID = s.nextInt();
		}
		
		System.out.println("Enter hours (int):");
		int hours = s.nextInt();
		
		try {
			
			ResultSet rs0 = database.getStatement().executeQuery("SELECT * FROM `cars` WHERE `ID` = '"+carID+"';");
			rs0.next();
			Car car = new Car();
			car.setID(rs0.getInt("ID"));
			car.setBrand(rs0.getString("Brand"));
			car.setModel(rs0.getString("Model"));
			car.setColor(rs0.getString("Color"));
			car.setYear(rs0.getInt("Year"));
			car.setPrice(rs0.getDouble("Price"));
			car.setAvailable(rs0.getInt("Available"));
			
			if (car.isAvailable()!=0) {
				System.out.println("Car isn't available");
				return;
			}
			
			 ResultSet rs1 = database.getStatement().executeQuery("SELECT MAX(ID) AS maxID FROM rents;");
			    rs1.next();
			    int ID = rs1.getInt("maxID") + 1;
			    if (rs1.wasNull()) {
			        ID = 0;
			    }
			
			double total = car.getPrice()*hours;
			
			Rent rent = new Rent();
			
			String insert = "INSERT INTO `rents`(`ID`, `User`, `Car`, `DateTime`, `Hours`, `Total`, `Status`) VALUES ('"+ID+"','"+user.getID()+"','"+car.getID()+"','"+rent.getDateTime()+"','"+hours+"','"+total+"','"+0+"');";
			
			database.getStatement().execute(insert);
			System.out.println("Car rented successfully");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
