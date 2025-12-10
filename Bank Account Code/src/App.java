/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* Main application class. This class is used to run the bank account
* project demonstrating inheritance and composition concepts.
*/
import java.sql.Connection;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        
        Scanner input = new Scanner(System.in);
        String dbName = "bank.db";
        Connection conn = SQLiteDatabase.connect(dbName);
        System.out.println("Bryson Weaver Week 4 Banking Accounts Project ");
        CustomerDAO customerDAO = new CustomerDAO();
        AccountDAO accountDAO = new AccountDAO();

        CustomerDAO.createCustomerTable(conn);
        AccountDAO.createAccountsTable(conn);

        System.out.println("=== Simple Banking App ===");

        while (true) {
            System.out.println("\n1. Create Customer");
            System.out.println("2. Create Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Account");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Customer Name: ");
                    String name = input.nextLine();
                    System.out.print("Email: ");
                    String email = input.nextLine();

                    Customer c = new Customer(name, email);
                    customerDAO.addCustomer(conn, c);

                    System.out.println("Customer created with ID: " + c.getCustomerID());
                    break;
                }

                case 2: {
                    System.out.print("Customer ID: ");
                    int custId = input.nextInt();

                    Customer owner = customerDAO.getCustomerById(conn, custId);
                    if (owner == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    System.out.println("1. Checking");
                    System.out.println("2. Savings");
                    System.out.print("Account type: ");
                    int t = input.nextInt();

                    System.out.print("Starting Balance: ");
                    double bal = input.nextDouble();

                    BankAccount newAcc;

                    if (t == 1) {
                        newAcc = new CheckingAccount(owner, bal);
                    } else {
                        newAcc = new SavingsAccount(owner, bal);
                    }

                    accountDAO.addAccount(conn, newAcc);
                    System.out.println("Account created!");
                    break;
                }

                case 3: {
                    System.out.print("Account ID: ");
                    int accId = input.nextInt();
                    System.out.print("Amount: ");
                    double amt = input.nextDouble();

                    BankAccount acc = accountDAO.getAccount(conn, accId);
                    if (acc == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    ITransactable tAcc = (ITransactable) acc;
                    tAcc.deposit(amt);

                    accountDAO.updateAccountBalance(conn, acc.getAccountID(), acc.getBalance());
                    System.out.println("Deposit successful.");
                    break;
                }

                case 4: {
                    System.out.print("Account ID: ");
                    int accId = input.nextInt();
                    System.out.print("Amount: ");
                    double amt = input.nextDouble();

                    BankAccount acc = accountDAO.getAccount(conn, accId);
                    if (acc == null) {
                        System.out.println("Account not found.");
                        break;
                    }

                    ITransactable tAcc = (ITransactable) acc;
                    tAcc.withdraw(amt);

                    accountDAO.updateAccountBalance(conn, acc.getAccountID(), acc.getBalance());
                    break;
                }

                case 5: {
                    System.out.print("Account ID: ");
                    int accId = input.nextInt();

                    BankAccount acc = accountDAO.getAccount(conn, accId);

                    if (acc == null) System.out.println("Account not found.");
                    else System.out.println("\n" + acc);

                    break;
                }

                case 6:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
