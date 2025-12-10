/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 21 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* savingAccount class extending BankAccount class. 
* The savingAcccount class represents a saving account with attributes and behaviors for
* saving accounts specifically, like an overdraft limit and an overdraft fee
*/
public class SavingsAccount extends BankAccount implements ITransactable {

    private final double interestRate; // since the interest rate is a set value, we make it final

    // this constructor is given only two parameters, and is given default values for the other properties

    public SavingsAccount(int accountID, Customer owner, double bal) { //demonstrates composition by using the customer class                                         
        super(accountID, owner, "Savings", bal); //customer class value as the parameter for the constructor
        this.interestRate = 0.02; // 2% interest
    }
    public SavingsAccount(Customer owner, double bal) {
        super(owner, "Savings", bal); 
        this.interestRate = 0.02; // 2% interest
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0)
            balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance)
            balance -= amount;
        else
            System.out.println("Savings Account: Cannot go negative.");
    }

    public void applyInterest() {
        balance += balance * interestRate;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Interest Rate: " + (interestRate * 100) + "%\n";
    }
}
