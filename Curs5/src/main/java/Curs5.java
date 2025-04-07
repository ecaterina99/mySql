import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Curs5 {

    public static void main(String[] args) {
        System.out.println("O sa ma conectez folosind DB Connection din generic-connection-01.jar");

        try {
            DBConnection dbConnection = new DBConnection();

            Connection connection = dbConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()) {
                String title = resultSet.getString("title");
                String isbn = resultSet.getString("isbn");
                System.out.println("Carte: " + title + " (ISBN: " + isbn + ")");
            }

        } catch (SQLException e) {
            System.err.println("Probleme SQL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Probleme IO: " + e.getMessage());
        }
    }
}
