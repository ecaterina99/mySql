import java.sql.SQLException;
public class DbPropsNotSetException extends RuntimeException {
  private static final String reason = "Database properties not set!";
    public DbPropsNotSetException() {
        super(reason);
    }
}
