/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* The Customer class represents a bank customer with basic attributes like name, email, and ID.
* it is used in composition with Account classes to associate customers with their accounts.
*/
import java.util.Random;

public class Customer {
    private String name;
    private String email;
    private final int id; // we make this final since we don't want it to change

    // the customer id is a random 4 digit integer that does not change, so we exclude it from the parameter list
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = randomIDGenerator();
    }
    // 4 digit random ID generation
    public static int randomIDGenerator () {
        Random rand = new Random();
        return 1000 + rand.nextInt(9000);
    }
    // Getters for Customer attributes
    public String getName() {
        return name; 
    }
    public String getEmail() { 
        return email; 
    }
    public int getId() {
        return id; 
    }
    // delete the customer account
    public void deleteAccount() {
        this.name = null;
        this.email = null;
        this.id = 0;
    }

    // Override toString for easy display of customer information
    @Override
    public String toString() {
        return ("Customer Name: " + name + "\nEmail: " + email + "\nID: " + id);
    }
}
