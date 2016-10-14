package magicianagentproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Database URL
    static final String DATABASE_URL = "jdbc:derby://localhost:1527/Magician Agent";
    private static Connection connection = null; // manages connection

    // Launching the application
    public DBConnection() {
        // connect to database
        try {
            // establish connection to database
            connection = (Connection) DriverManager.getConnection(
                    DATABASE_URL, "adityakela", "Optical@142");
        } //end try 
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }   //end catch 
    }

    public static Connection getConnection() {
        return connection;
    }

}
