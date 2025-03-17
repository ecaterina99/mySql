import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private Properties dbProperties;
//    private final String DB_PROPS_FILE = "mysql.properties";
    private Connection connection;

    DBConnection() throws SQLException, IOException {
        //setDBPropertiesViaFile();
        setDbPropertiesENV();
        connect();
    }

    void setDbPropertiesENV() {
        String mysqlUserName = System.getenv("mysql_username");
        String mysqlPassword = System.getenv("mysql_password");

        dbProperties = new Properties();
        dbProperties.setProperty("user", mysqlUserName);
        dbProperties.setProperty("password", mysqlPassword);
    }

//    void setDBPropertiesViaFile() throws IOException {
//        dbProperties = new Properties();
//        dbProperties.load(new FileInputStream(DB_PROPS_FILE));
//    }

    void connect() throws SQLException {
        String connectionStr = "jdbc:mysql://localhost:3306/work";

        if (dbProperties == null) {
            throw new DbPropsNotSetException();
        }
        connection=DriverManager.getConnection(connectionStr, dbProperties);
    }

    public Connection getConnection() throws SQLException {
        return connection;
    }

}

//void setDBProperties() {
//    dbProperties = new Properties();
//    dbProperties.setProperty("user", "root");
//    dbProperties.setProperty("password", "123123");
//}
