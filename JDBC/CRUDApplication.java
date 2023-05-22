import java.sql.*;
import java.util.Scanner;

public class CRUDApplication {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase"; 
    private static final String USERNAME = "your-username";
    private static final String PASSWORD = "your-password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("CRUD Application");
                System.out.println("1. Add record");
                System.out.println("2. View record");
                System.out.println("3. Update record");
                System.out.println("4. Delete record");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addRecord(connection);
                        break;
                    case 2:
                        viewRecord(connection);
                        break;
                    case 3:
                        updateRecord(connection);
                        break;
                    case 4:
                        deleteRecord(connection);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addRecord(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        String insertQuery = "INSERT INTO mytable (name, age) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Record added successfully.");
            } else {
                System.out.println("Failed to add record.");
            }
        }
    }

    private static void viewRecord(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String selectQuery = "SELECT * FROM mytable";
        ResultSet resultSet = statement.executeQuery(selectQuery);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
        }

        resultSet.close();
        statement.close();
    }

    private static void updateRecord(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of the record to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();

        String updateQuery = "UPDATE mytable SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setString(1, newName);
            statement.setInt(2, newAge);
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Record updated successfully.");
            } else{
                System.out.println("No record found with ID: " + id);
            }
        }
    }
    private static void deleteRecord(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID of the record to delete: ");
        int id = scanner.nextInt();
    
        String deleteQuery = "DELETE FROM mytable WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record deleted successfully.");
            } else {
                System.out.println("No record found with ID: " + id);
            }
        }
    }
}
    
    
