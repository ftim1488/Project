import java.sql.*;


public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restaurantDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }


    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Connected to MySQL!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

