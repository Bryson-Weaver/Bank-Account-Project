/*
* Class to handle database interactions with a SQLite database. The
* connect method will either connect to an existing database or
* create the database if the database doesn't exist. 
*/
import java.sql.Connection; // this is the package for handling database connections
import java.sql.DriverManager; // this is the package for managing JDBC drivers
import java.sql.SQLException; // this is the package for handling SQL exceptions

public class SQLiteDatabase {

    // method to connect to a SQLite database
    public static Connection connect (String dbName) {
        Connection conn = null;
        
        // SQLite connection string, the database file is specified by dbName, if the file does not exist, SQLite will create it.
        // this connection string uses the SQLite JDBC driver to connect to the database or create it
        String url = "jdbc:sqlite:" + dbName;
        
        try {
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
