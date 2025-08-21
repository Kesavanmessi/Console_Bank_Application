package view;

public class CustomerView {
    public void displayServicesMenu() {
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw  Money from ATM");
        System.out.println("3. Transfer Money");
        System.out.println("4. View Balance");
        System.out.println("5. View Transaction History");
        System.out.println("6. Change Password");
        System.out.println("7. Logout");
    }
    public void displayMainMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }
    public void displayWelcomeMessage() {           
        System.out.println("Welcome to the Bank Application!");
    }   
    
}
