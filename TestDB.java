package Model;

public class TestDB {
    public static void main(String[] args) {
        Model.Database db = new Model.Database();
        if (db.getConnection() != null) {
            System.out.println("✅ Success: Connected to the database!");
        } else {
            System.out.println("❌ Failed: Could not connect.");
        }
        db.close();
    }
}

