import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args){

        String sql;
        try {
            sql = Files.readString(Path.of("sql/populate_db.sql"));
        } catch (IOException e) {
            throw new RuntimeException("failed to read populate_db.sql", e);
        }

        try (Statement statement = Database.getInstance().getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("failed to execute populate_db.sql", e);
        }
    }
}
