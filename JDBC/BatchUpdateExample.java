import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class BatchUpdateExample {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USERNAME = "your-username"; 
    private static final String PASSWORD = "your-password"; 

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String filePath = "input.txt"; 
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String insertQuery = "INSERT INTO mytable (name, age) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0].trim();
                int age = Integer.parseInt(data[1].trim());

                statement.setString(1, name);
                statement.setInt(2, age);

                statement.addBatch();
            }

            int[] result = statement.executeBatch();

            int totalRecordsInserted = 0;
            for (int i : result) {
                if (i >= 0) {
                    totalRecordsInserted += i;
                }
            }

            System.out.println("Total records inserted: " + totalRecordsInserted);
            statement.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
