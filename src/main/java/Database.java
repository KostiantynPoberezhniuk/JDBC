import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DB_URL = "jdbc:h2:./megasoftV2";


    private static Database instance;
    private static Connection connection;

    private Database() {
        try {
            connection = DriverManager.getConnection(DB_URL, "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed", e);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

}
