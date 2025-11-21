/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* Main application class. This class is used to run the bank account
* project demonstrating inheritance and composition concepts.
*/
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Project week 2 - Bank Account Application\n Created by Bryson Weaver\n");
        
        System.out.println("Welcome to the Bank Account Application!");
        System.out.print("You will create an account, be given a checking and savings account, ");
        System.out.println("and perform transactions");

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your email: ");
        String email = input.nextLine();

        Customer customer = new Customer(name, email);
        CheckingAccount checking = new CheckingAccount(customer);
        SavingsAccount savings = new SavingsAccount(customer);
        
        System.out.println("You now have a checking and savings account.");
        // Menu loop 
        boolean running = true;

        while (running) {

            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. View Account Details");
            System.out.println("4. Exit Application");
            System.out.print("Enter your choice: ");
            System.out.println();

            int action = input.nextInt();

            switch (action) {
                case 1:
                    System.out.println("Please enter which account you want to deposit into: ");
                    System.out.println("1. Checking Account");
                    System.out.println("2. Savings Account");
                    byte depositChoice = input.nextByte();
                    
                    // Polymorphism example:
                    // Using ITransactable interface reference to hold the chosen account object
                    ITransactable depositAccount;

                    if (depositChoice == 1){
                        depositAccount = checking;  // CheckingAccount implements ITransactable
                    } else if (depositChoice == 2){
                        depositAccount = savings;   // SavingsAccount implements ITransactable
                    } else {
                        System.out.println("Invalid Choice. Try again.");
                        break;
                    }
                    System.out.println("Please enter deposit amount: ");
                    double depositAmount = input.nextDouble();

                    // Interface method call - polymorphism in action
                    depositAccount.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Please enter which account you want to withdraw from: ");
                    System.out.println("1. Checking Account");
                    System.out.println("2. Savings Account");
                    System.out.println();
                    byte withdrawChoice = input.nextByte();

                    // Polymorphism example:
                    // Interface reference for withdrawal
                    ITransactable withdrawAccount;

                    if (withdrawChoice == 1){
                        withdrawAccount = checking;
                    } else if (withdrawChoice == 2){
                        withdrawAccount = savings;
                    } else {
                        System.out.println("Invalid Choice. Try again.");
                        break;
                    }

                    System.out.println("Please enter withdraw amount: ");
                    double withdrawAmount = input.nextDouble();

                    // Interface method call - polymorphism demonstrated
                    withdrawAccount.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("\n--- Account Details ---");
                    System.out.println(checking);
                    System.out.println();
                    System.out.println(savings);
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
        }
    }
}
