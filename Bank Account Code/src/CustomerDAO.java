/*
    Class for creating a customer table if one does not exist, as well
    as it's CRUD operations
*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO {
    
    public static boolean createCustomerTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Customers (" + 
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT," +
                     "email TEXT" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addCustomer(Connection conn, Customer customer) {
        String sql = "INSERT INTO Customers (name, email) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getCustomerEmail());
            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                customer.setCustomerID(keys.getInt(1)); // FIXED
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "SELECT * FROM Customers WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer getCustomerByName(Connection conn, String name) {
        String sql = "SELECT * FROM Customers WHERE name = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateCustomerInfo(Connection conn, Customer c) {
        String sql = "UPDATE Customers SET name = ?, email = ? WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getCustomerName());
            pstmt.setString(2, c.getCustomerEmail());
            pstmt.setInt(3, c.getCustomerID());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating customer: " + e.getMessage());
        }
    }

    public boolean deleteCustomer(Connection conn, int id) {
        String sql = "DELETE FROM Customers WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            return false;
        }
    }
}
