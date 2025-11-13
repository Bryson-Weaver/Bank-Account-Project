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
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in); // Scanner for user input
        boolean accountFound = true; //while loop control variable

        // informative header
        System.out.println("Week 1 Project Demo - Inheritance, Composition, and User Interactions \nCreated by Bryson Weaver\n");
        try {
            while (accountFound) { //The block of code below will continue to run until the user enter enters their account or creates a new one
                // Welcome message
                System.out.println("Hello, welcome to my Bank Account Project!");
                System.out.println("Do you have an existing account with us? (yes/no): ");
                String hasAccount = input.nextLine().trim().toLowerCase(); // reads user input as one word, lowercase, with no spaces

                if (hasAccount.equals("yes")) {
                    System.out.println("Welcome back! Please enter your customer account name: ");
                    String accountName = input.nextLine().trim();
                    // This is where we would check if the account is in a database / arrayList
                    System.out.println("Account for " + accountName + " was found. You can now perform transactions.(not implemented yet)");
                    accountFound = false;
                } else if (hasAccount.equals("no")) {
                    System.out.println("Let's create a new customer account for you.");
                    //prompt the user to enter their name and email
                    System.out.print("Please Enter your name: ");
                    String name = input.nextLine().trim();
                    System.out.print("Enter your email: ");
                    String email = input.nextLine().trim();
                    // instantiating a customer object with the name and email we got from the user as parameters
                    Customer newCustomer = new Customer(name, email); 
                    System.out.println("Account created successfully!\n");
                    System.out.println("Here are your account details:\n" + newCustomer + "\n\n"); // displays the contents of the Customer class 
                    System.out.println("Here is your checking account details:");
                    CheckingAccount newCheckingAccount = new CheckingAccount(newCustomer); //instantiates a checking account object that uses the name from the customer as the parameter for the constructor
                    //newCheckingAccount.deposit(new BigDecimal(100.00));
                    System.out.println(newCheckingAccount); //displays the checking account information
                    accountFound = false;
                } else {
                    System.out.println("Invalid input. Please restart and enter 'yes' or 'no'.");
                    System.out.println(); // clear invalid input
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Please enter valid input.");
        }
        /*
        System.out.println("Please choose from one of the options:");
        System.out.println("1. Create checking account");
        System.out.println("2. Create savings account");
        System.out.println("3. Deposit into an account");
        System.out.println("4. Withdraw from an account");
        System.out.println("5. Fetch bank account details");
        */
        // close scanner to prevent resource leaks
        input.close();
    }
}
