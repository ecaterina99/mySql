import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.getConnection();
            System.out.println("Connected to the database");

            String query = "Select * from employees where id>? AND firstName LIKE ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, 6);
            ps.setString(2, "%a%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                System.out.println("Found employee: " + firstName);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("No connection established: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("No file connection established: " + e.getMessage());
        }

    }
}
