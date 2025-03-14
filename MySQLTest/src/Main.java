import java.sql.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
//        System.out.println("Received " + args.length + " arguments");
//        for(int i=0;i<args.length;i++) {
//            System.out.println("Argument " + i + " is "+args[i]);
//        }

        String database = args[0];
        String user = args[1];
        String password = args[2];

        MySQL mySQL = new MySQL(database, user, password);
        mySQL.selectRows("employees");
        String[] data = mySQL.selectRow("employees", 2);
        System.out.println("Employee has the following data: " + Arrays.toString(data));

//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/work", "root", "123123");
//
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");
//
//        while (resultSet.next()) {
//            String firstName = resultSet.getString("firstName");
//            String lastName = resultSet.getString("lastName");
//            Date birthDate = resultSet.getDate("birthDate");
//            Integer salary = resultSet.getInt("salary");
//            String gen = resultSet.getString(6);
//            System.out.println("Name: " + firstName + " " + lastName + ", birthday: " + birthDate + ", gender: " + gen + ", salary: " + salary + "$");
//        }

        int[] idToDelete = {3, 4, 5};
        for (int i = 0; i < idToDelete.length; i++) {
            boolean deleted = mySQL.deleteRow("employees", idToDelete[i]);
            if (deleted) {
                System.out.println("Employee with id " + idToDelete[i] + " was deleted.");
            } else {
                System.out.println("Employee with id " + idToDelete[i] + " was not deleted.");
            }
        }
    }
}