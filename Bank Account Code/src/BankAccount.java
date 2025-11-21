/* 
 * Name: Bryson Weaver
 * Date: 11/21/2025
 * Purpose: Simple parent class for Checking and Savings accounts.
 */

import java.util.Random;

public class BankAccount {
    private final int accountNumber;
    private final Customer owner;
    protected double balance;
    protected String accountType;

    public BankAccount(Customer owner, String accountType) {
        this.owner = owner;
        this.accountType = accountType;
        this.accountNumber = randomAccNumGenerator();
        this.balance = 0.0;
    }
    public static int randomAccNumGenerator () {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000);
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

