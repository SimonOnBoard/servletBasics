package singletones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String userName = "simon";
    private static final String password = "12345";
    private static final String url = "jdbc:postgresql://localhost:5432/test_learning";
    private static final String driverClassName = "org.postgresql.Driver";

    //private Connection connection;

    private ConnectionProvider() throws ClassNotFoundException {
        Class.forName(driverClassName);
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
