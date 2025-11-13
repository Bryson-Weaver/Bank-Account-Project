/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* The Account class serves as a base class for different types of bank accounts, and uses composition
* to include common attributes and behaviors shared among various account types. It can add an account name,
* as well as display account details like 
*/
import java.math.BigDecimal; // For precise monetary calculations
import java.util.Random; // For generating random account numbers

public class CustomerBankAccount {
    
    private int accountNumber;
    private Customer customerName; // Using Customer class for account customerName via composition
    private String accountType;
    private BigDecimal balance; // Demonstrating composition with BigDecimal for monetary values

    // Constructor to initialize account with accountNumber, customerName, account type, and initial balance
    public CustomerBankAccount(Customer customerName, String accountType) {
        this.customerName = customerName;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO; // Initialize balance to zero
        this.accountNumber = generateAccountNumber(); // Generate a random account number
    }
    // Get account customerName
    public Customer getCustomerName() {
        return customerName;
    }
    // Deposit amount into account if amount is positive
    public void deposit(BigDecimal amount) {
        try {
            while (true) {
                if (amount != null && amount.signum() > 0) {
                balance = balance.add(amount);
                break;
                }
                    else {
                        System.out.println("Deposit amount must be positive.");
                        System.out.println(); //clear invalid input
                    }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a numeric value for deposit.");
        }
    }

    // Withdraw amount from account if it does not exceed overdraft limit
    public void withdraw(BigDecimal amount) {
        try {    
            while(true) {
                if (amount != null && amount.signum() > 0 && balance.compareTo(amount) >= -500.00) {
                    balance = balance.subtract(amount);
                    break;
                }
                else {
                    System.out.println("Withdrawal exceeds overdraft limit."); // Simple overdraft check
                    System.out.println(); //clear invalid input
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter a numeric value to withdrawal.");
        }
    }
    // Get account number
    public int getAccountNumber() {
        return accountNumber;
    }
    // Generate a random account number
    public int generateAccountNumber() {
        Random rand = new Random();
        return 100000 + rand.nextInt(900000); // Generates a random 6-digit account number
    }
    
    // format account details when printed
    @Override
    public String toString() {
        return String.format("Account Number: %d\n Account Name: %s\n Account Type: %s\n Balance: $%s",
                accountNumber, customerName.getName(), accountType, balance.toString());
    }
}
