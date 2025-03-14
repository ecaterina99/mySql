import java.sql.*;
import java.util.Arrays;

public class MySQL {
    private Connection connection;

    MySQL(String database, String user, String password) throws SQLException {
        connect(database, user, password);
    }

    void connect(String database, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, password);
    }

    void selectRows(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberColumns = metaData.getColumnCount();
        while (resultSet.next()) {
            String[] columnValues = new String[numberColumns];
            for (int i = 1; i <= numberColumns; i++) {
                String columnName = resultSet.getString(i);
                columnValues[i-1] = columnName;
            }
            System.out.println("Values for column row: " + Arrays.toString(columnValues));
        }
    }

    String[] selectRow(String tableName, int id) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE id = " + id);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberColumns = metaData.getColumnCount();
        String[] columnValues = new String[numberColumns];
        if (resultSet.next()) {
            for (int i = 1; i <= numberColumns; i++) {
                String columnName = resultSet.getString(i);
                columnValues[i-1] = columnName;
            }
            System.out.println("Values for column row: " + Arrays.toString(columnValues));
        }
        return columnValues;
    }

    boolean deleteRow(String tableName, int id) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate("DELETE FROM " + tableName + " WHERE id = " + id) == 1;
    }
}
