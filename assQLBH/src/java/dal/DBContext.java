package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    protected Connection connection;

    // Constructor to initialize the connection to SQL Server
    public DBContext() {
        try {
            // Edit URL, username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://XUANGIANG\\SQLEXPRESS:1433;databaseName=shopDb";  // Edit server name, instance, and database
            String username = "sa";  // Your SQL Server username
            String password = "12345";  // Your SQL Server password

            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);

            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connection to the database was successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            // Print error message if any exception occurs
            System.out.println("Error: " + ex.getMessage());
        }
    }

    // Main method to test the database connection
    public static void main(String[] args) {
        // Create an instance of DBContext to trigger connection
        DBContext db = new DBContext();
    }
}
