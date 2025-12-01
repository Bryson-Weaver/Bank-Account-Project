/* 
 * Name: Bryson Weaver
 * Date: 11/21/2025
 * Purpose: Simple interface for deposit and withdraw actions.
 */

// this interface can be an example of an abstract class, a class that is a blueprint
// for classes that inherit it
public interface ITransactable { // This is creating an interface for any class that has transactions
    void deposit(double amount);
    void withdraw(double amount);
}
