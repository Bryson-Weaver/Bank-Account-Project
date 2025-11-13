import java.math.BigDecimal;

/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* CheckingAccount class extending Account class. 
* The CheckingAcccount class represents a checking account with attributes and behaviors for
* checking accounts specifically, like an overdraft limit and an overdraft fee
*/

public class CheckingAccount extends CustomerBankAccount { //demonstrating inheritance from CustomerAccount class
    private double overdraftLimit;
    private int overdraftFees;
    private BigDecimal balance; // CheckingAccount maintains its own balance separate from the superclass

    // Constructors to initialize CheckingAccount with customer name and optional initial balance
    public CheckingAccount(Customer name) {
        super(name, "Checking"); // keep superclass initialization (account number, owner, type)
        this.overdraftLimit = 500.00; // Default overdraft limit
        this.overdraftFees = 0;
        this.balance = BigDecimal.ZERO;
    }
    // Get overdraft limit
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    // Set overdraft limit
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
    // Get fees
    public int getOverDraftFee() {
        return overdraftFees;
    }
    // Set fees
    public void setFees(int fees) {
        this.overdraftFees = fees;
    }

    public String getAccountType() {
        return "Checking";
    }

    // CheckingAccount uses its own balance field
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance == null ? BigDecimal.ZERO : newBalance;
    }

    // Simple deposit/withdraw helpers that operate on this account's balance
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) return;
        this.balance = this.balance.add(amount);
    }


    @Override
    public String toString() {
        return String.format(
                "(inherited) Account Number: %d\n(inherited) Account Owner Name: %s\n(inherited) Account Type: %s\nBalance: $%s\nOverdraft Limit: %.2f\nOverdraft Fees: %d",
                getAccountNumber(), getCustomerName().getName(), getAccountType(), getBalance().toString(),
                getOverdraftLimit(), getOverDraftFee());
    }
}