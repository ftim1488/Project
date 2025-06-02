import java.sql.*;

public class EmployeeDAO {
    private final String url = "jdbc:mysql://localhost:3306/companydb";
    private final String user = "root";      // Change if needed
    private final String password = "";      // Change if needed

    // Inserts employee into database
    public void saveEmployee(Employee emp, String type, double salary, int hours) {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO employees (id, name, type, salary, hours) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, type);
            ps.setDouble(4, salary);
            ps.setObject(5, (type.equals("part") ? hours : null)); // NULL for full-time

            ps.executeUpdate();
            System.out.println("Employee saved to database.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
