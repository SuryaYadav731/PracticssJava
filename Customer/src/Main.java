import java.sql.*;

public class Main {
    // JDBC URL, username, and password of MySQL server
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/customer"; // Change to your database name
    static final String USER = "root";  // Change to your DB username
    static final String PASSWORD = "Surya@7310";  // Change to your DB password

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Open a connection
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");

            // Step 3: Create table if not exists
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Cust (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(50)," +
                    "phone VARCHAR(15))";
            pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();
            System.out.println("Table 'Cust' created successfully.");

            // Step 4: Insert customer data
            String insertSQL = "INSERT INTO Cust (name, email, phone) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, "Surya Yadav");  // Example customer name
            pstmt.setString(2, "suryayadav222003@gmail.com");  // Example email
            pstmt.setString(3, "7272807401");  // Example phone number
            pstmt.executeUpdate();
            System.out.println("Customer data inserted successfully.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Step 5: Clean up environment
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
