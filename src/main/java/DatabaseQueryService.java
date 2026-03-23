import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    private String readSql(String fileName) {
        try {
            return Files.readString(Path.of("sql/" + fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read " + fileName, e);
        }
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> result = new ArrayList<>();
        String sql = readSql("find_longest_project.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new LongestProject(
                        rs.getInt("id"),
                        rs.getInt("months_count")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        List<MaxProjectCountClient> result = new ArrayList<>();
        String sql = readSql("find_max_projects_client.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new MaxProjectCountClient(
                        rs.getString("name"),
                        rs.getInt("project_count")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String sql = readSql("find_max_salary_worker.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new MaxSalaryWorker(
                        rs.getString("name"),
                        rs.getDouble("salary")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> result = new ArrayList<>();
        String sql = readSql("find_youngest_eldest_workers.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new YoungestEldestWorker(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getDate("birthday").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<ProjectPrice> printProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();
        String sql = readSql("print_project_prices.sql");

        try (Statement stmt = Database.getInstance().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new ProjectPrice(
                        rs.getInt("name"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static void main(String[] args) {
        DatabaseInitService.main(args);
        DatabasePopulateService.main(args);

        DatabaseQueryService queryService = new DatabaseQueryService();

        System.out.println("=== Longest Project ===");
        queryService.findLongestProject().forEach(System.out::println);

        System.out.println("\n=== Max Projects Client ===");
        queryService.findMaxProjectsClient().forEach(System.out::println);

        System.out.println("\n=== Max Salary Worker ===");
        queryService.findMaxSalaryWorker().forEach(System.out::println);

        System.out.println("\n=== Youngest & Eldest Workers ===");
        queryService.findYoungestEldestWorkers().forEach(System.out::println);

        System.out.println("\n=== Project Prices ===");
        queryService.printProjectPrices().forEach(System.out::println);
    }
}