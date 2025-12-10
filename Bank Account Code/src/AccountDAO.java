

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private CustomerDAO customerDAO = new CustomerDAO();

    public static boolean createAccountsTable(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Accounts (" +
                     "account_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "customer_id INTEGER NOT NULL," +
                     "account_type TEXT NOT NULL," +
                     "balance REAL NOT NULL," +
                     "FOREIGN KEY(customer_id) REFERENCES Customers(id)" +
                     ");";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return true;

        } catch (SQLException e) {
            System.out.println("Error creating Accounts table: " + e.getMessage());
            return false;
        }
    }

    public void addAccount(Connection conn, BankAccount account) {
        String sql = "INSERT INTO Accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, account.getOwner().getCustomerID());
            stmt.setString(2, account.getAccountType());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();

            // set generated ID on the Java object
            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                account.setAccountID(keys.getInt(1));
            }

        } catch (SQLException e) {
            System.out.println("Error inserting account: " + e.getMessage());
        }
    }

    public BankAccount getAccount(Connection conn, int accountID) {
        String sql = "SELECT * FROM Accounts WHERE account_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int custId   = rs.getInt("customer_id");
                String type  = rs.getString("account_type");
                double bal   = rs.getDouble("balance");

                Customer owner = customerDAO.getCustomerById(conn, custId);

                if (type.equalsIgnoreCase("Checking")) {
                    return new CheckingAccount(accountID, owner, bal);
                } else {
                    return new SavingsAccount(accountID, owner, bal);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving account: " + e.getMessage());
        }

        return null;
    }

    public List<BankAccount> getAccountsForCustomer(Connection conn, int customerId) {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Accounts WHERE customer_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            Customer owner = customerDAO.getCustomerById(conn, customerId);

            while (rs.next()) {
                int accID   = rs.getInt("account_id");
                String type = rs.getString("account_type");
                double bal  = rs.getDouble("balance");

                if (type.equalsIgnoreCase("Checking")) {
                    accounts.add(new CheckingAccount(accID, owner, bal));
                } else {
                    accounts.add(new SavingsAccount(accID, owner, bal));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving accounts for customer: " + e.getMessage());
        }

        return accounts;
    }

    public void updateAccountBalance(Connection conn, int accountID, double newBalance) {
        String sql = "UPDATE Accounts SET balance = ? WHERE account_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating account balance: " + e.getMessage());
        }
    }

    public boolean deleteAccount(Connection conn, int accountID) {
        String sql = "DELETE FROM Accounts WHERE account_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountID);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting account: " + e.getMessage());
            return false;
        }
    }

    public List<BankAccount> getAllAccounts(Connection conn) {
        List<BankAccount> accounts = new ArrayList<>();
        String sql = "SELECT * FROM Accounts";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int accID     = rs.getInt("account_id");
                int custId    = rs.getInt("customer_id");
                String type   = rs.getString("account_type");
                double bal    = rs.getDouble("balance");

                Customer owner = customerDAO.getCustomerById(conn, custId);

                if (type.equalsIgnoreCase("Checking")) {
                    accounts.add(new CheckingAccount(accID, owner, bal));
                } else {
                    accounts.add(new SavingsAccount(accID, owner, bal));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listing all accounts: " + e.getMessage());
        }

        return accounts;
    }
}