import java.sql.*;

public class Main {
    // JDBC URL, username, and password of MySQL server
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/customer"; // Replace with your DB name
    static final String USER = "root";  // Replace with your DB username
    static final String PASSWORD = "Surya@7310";  // Replace with your DB password

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Step 1: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Open a connection
            conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");

            // Step 3: Delete customer record by ID
            String deleteSQL = "DELETE FROM Cust WHERE id = ?";
            pstmt = conn.prepareStatement(deleteSQL);
            pstmt.setInt(1, 2);  // Assuming we want to delete the customer with ID = 2

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Customer with ID 2 deleted successfully.");
            } else {
                System.out.println("No customer found with the specified ID.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Step 4: Clean up environment
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
