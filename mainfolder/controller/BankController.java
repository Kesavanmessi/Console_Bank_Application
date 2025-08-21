package controller;

import java.util.Scanner;
import service.BankServices;
import view.CustomerView;

/**
 * BankController class to manage bank operations.
 */

public class BankController {

    BankServices bankServices;
    CustomerView customerView;
    Scanner sc = new Scanner(System.in);

    public BankController() {
        this.bankServices = new BankServices();
        this.customerView = new CustomerView();
    }

    public void start() {
        customerView.displayWelcomeMessage(); 
                
        while (true) {
            customerView.displayMainMenu();
            int choice = sc.nextInt();

            switch(choice) {

                case 1:
                this.createAccount();
                break;

                case 2:
                login();   
                break;  
                
                case 3:
                System.out.println("Thank you for using our services!");  
                return;
                
               default:
               System.out.println("Invalid choice!");
           }
     }
    }

    public void createAccount() {
       System.out.println("Enter your Name");
       sc.nextLine();
       String accountHolderName = sc.nextLine();
       System.out.println("Account created successfully! with initial balance of 10000.");
       System.out.println("Please Remember your customer_ID and password that given below for future reference and Login.");
       System.out.println(bankServices.createAccount(accountHolderName));

    }                  

    public void servicesAvailable(String customerId) {
        while (true) {

            customerView.displayServicesMenu();
            int serviceChoice = sc.nextInt();

            switch(serviceChoice) {
                case 1:
                    bankServices.deposit(customerId);
                    break;
                case 2:
                    bankServices.ATMwithdraw(customerId);
                    break;
                case 3:
                    bankServices.transfer(customerId);
                    break;
                case 4:
                    bankServices.viewBalance(customerId);
                    break;  
                case 5:
                    bankServices.viewTransactionHistory(customerId);
                    break;
                case 6:
                    bankServices.managePassword(customerId);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return; // Exit the services menu
                default:
                    System.out.println("Invalid choice! Please try again.");
                  
            }
    }

}
    

    public void login(){

        System.out.println("Enter your Customer ID:");
        sc.nextLine();
        String customerId = sc.nextLine();              
        System.out.println("Enter your Password:");
        String password = sc.nextLine();    
      
        if(bankServices.login(customerId, password)) {
            System.out.println("Login successful!");
            servicesAvailable(customerId);
        }
        else {
            System.out.println("Login failed! Due to Invalid Credentials.");
        }

    }
    
}
