/*******************************************************************
* Name: Bryson Weaver
* Date: 11 / 13 / 2025
* Week 1 Project Demo - Inheritance, Composition, and User Interactions
*
* The Customer class represents a bank customer with basic attributes like name, email, and ID.
* it is used in composition with Account classes to associate customers with their accounts.
*/

public class Customer {
    private String name;
    private String email;
    private int id; 

    // the customer id is a assigned in the database
    public Customer(int id, String name, String email) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    
    public Customer(String name, String email){
        this.id = -1;
        this.name = name;
        this.email = email;
    }

    public void setCustomerID(int id){
        this.id = id;
    }
    // Getters for Customer attributes
    public String getCustomerName() {
        return name; 
    }
    public void setCustomerName(String n){
        this.name = n;
    }
    public String getCustomerEmail() { 
        return email; 
    }
    public void setCustomerEmail(String e){
        this.email = e;
    }
    public int getCustomerID() {
        return id; 
    }

    // Override toString for easy display of customer information
    @Override
    public String toString() {
        return ("Customer Name: " + name + "\nEmail: " + email + "\nID: " + id);
    }
}