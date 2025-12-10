/* 
 * Name: Bryson Weaver
 * Date: 11/21/2025
 * Purpose: Simple parent class for Checking and Savings accounts.
 */

public class BankAccount {
    private int accountID; // since acc num and owner are concrete, we make them final
    private final Customer owner;
    protected double balance;
    protected String accountType; // we want these properties to be accessible to it's subclasses

    public BankAccount(int accountID, Customer owner, String accountType, double bal) {
        this.owner = owner;
        this.accountType = accountType;
        this.accountID = accountID;
        this.balance = bal;
    }
    public BankAccount(Customer owner, String accountType, double bal) {
        this.owner = owner;
        this.accountType = accountType;
        this.balance = bal;
    }
    public void setAccountID(int accID){ this.accountID = accID; }
    public int getAccountID() { return accountID; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public Customer getOwner() { return owner; }

    @Override
    public String toString() {
        return "Account Type: " + accountType +
               "\nAccount ID: " + accountID +
               "\nOwner: " + owner.getCustomerName() +
               "\nBalance: $" + balance + "\n";
    }
}