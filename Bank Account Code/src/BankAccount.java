/* 
 * Name: Bryson Weaver
 * Date: 11/21/2025
 * Purpose: Simple parent class for Checking and Savings accounts.
 */

import java.util.Random;

public class BankAccount {
    private final int accountNumber; // since acc num and owner are concrete, we make them final
    private final Customer owner;
    protected double balance;
    protected String accountType; // we want these properties to be accessible to it subclasses

    // this constructor only has parameters
    public BankAccount(Customer owner, String accountType) {
        this.owner = owner;
        this.accountType = accountType;
        this.accountNumber = randomAccNumGenerator();
        this.balance = 0.0;
    }
    public static int randomAccNumGenerator () {
        return Customer.randomIDGenerator();
    }

    public int getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    public Customer getOwner() { return owner; }

    @Override
    public String toString() {
        return "Account Type: " + accountType +
               "\nAccount Number: " + accountNumber +
               "\nOwner: " + owner.getName() +
               "\nBalance: $" + balance + "\n";
    }
}

