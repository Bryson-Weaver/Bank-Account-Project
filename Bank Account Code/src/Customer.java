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
    private int id; // e.g. customer id

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = randomIDGenerator();
    }
    // 4 digit random ID generation
    public int randomIDGenerator () {
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
    //setters for customer attributes
    public void setName(String customerName) {
        this.name = customerName;
    }
    public void setEmail(String customerEmail) {
        this.email = customerEmail;
    }
    public void setNewID(int customerName) {
        Random rand = new Random();
        this.id = 1000 + rand.nextInt(9000);
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
