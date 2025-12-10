/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* CheckingAccount class extending BankAccount class. 
* The CheckingAcccount class represents a checking account with attributes and behaviors for
* checking accounts specifically, like an overdraft limit and an overdraft fee
*/

public class CheckingAccount extends BankAccount implements ITransactable {

    // both of these properties are set values, so they will be given the final access modifier
    private final double overdraftLimit;
    private final double overdraftFee;

    public CheckingAccount(int accountID, Customer owner, double bal) {
        super(accountID, owner, "Checking", bal);
        this.overdraftLimit = 200.0; // unique property
        this.overdraftFee = 35.0;    // unique property
    }

    public CheckingAccount(Customer owner, double bal) {
        super(owner, "Checking", bal);
        this.overdraftLimit = 200.0; // unique property
        this.overdraftFee = 35.0;
    }
    @Override // overriding interface implemented method
    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    @Override // overriding interface implemented method
    public void withdraw(double amount) {
        double maxWithdraw = balance + overdraftLimit;

        if (amount <= balance) {
            balance -= amount;
        }
        else if (amount <= maxWithdraw) {
            balance -= (amount + overdraftFee);
            System.out.println("Checking overdraft used. Fee applied: $" + overdraftFee);
        } 
        else {
            System.out.println("Withdrawal denied. Overdraft limit exceeded for Checking Account.");
        }
    }

    @Override
    public String toString() {
        return super.toString() +
               "Overdraft Limit: $" + overdraftLimit + "\n" +
               "Overdraft Fee: $" + overdraftFee + "\n";
    }
}
