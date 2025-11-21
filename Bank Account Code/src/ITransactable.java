/* 
 * Name: Bryson Weaver
 * Date: 11/21/2025
 * Purpose: Simple interface for deposit and withdraw actions.
 */

public interface ITransactable { // This is creating an interface for any class that has transactions
    void deposit(double amount);
    void withdraw(double amount);
}
