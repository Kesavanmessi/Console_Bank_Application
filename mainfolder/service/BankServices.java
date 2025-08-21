package service;
import java.util.ArrayList;
import model.Customer_Account;
import model.Statement;
import java.util.HashMap;
import java.util.Map;   
import java.util.Scanner;
import java.util.List;
public class BankServices {
    private Map<String, Customer_Account> accounts;
    private List<Statement> transactionHistory; // Maps transaction ID to details
    private Scanner sc;
    private int customerIdCounter = 1000; // Starting customer ID from 1000
    private int transactionIdCounter = 1; // Starting transaction ID from 1000
    private int accountNoCounter = 10000; // Starting account number from 10000

    public BankServices() {
        this.accounts = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }
    

    public String createAccount(String accountHolderName) {
        String customerId = "CAN"+String.valueOf(customerIdCounter++);
        String accountNo = String.valueOf(accountNoCounter++);
        String password = "AAaa77"; // Assume this method generates a secure password    
        StringBuilder encryptedPass = new StringBuilder(); 
        for (char c : password.toCharArray()) {
            encryptedPass.append((char)(c + 1)); // Simple encryption logic for demonstration
        }
        Customer_Account newAccount = new Customer_Account(customerId, accountNo, accountHolderName,encryptedPass.toString());
        accounts.put(customerId, newAccount);   
        return "Customer ID: " + customerId + ", Account No: " + accountNo + ", Password: " + password;
    }

    public boolean login(String customerId, String password) {
        Customer_Account account = accounts.get(customerId);    
        if (account != null) {
            String encryptedPassword = account.getPassword();
            StringBuilder decryptedPass = new StringBuilder();
            for (char c : encryptedPassword.toCharArray()) {
                decryptedPass.append((char)(c - 1)); // Simple decryption logic for demonstration
            }       
            return decryptedPass.toString().equals(password);
        }
        return false;
    }

    public void deposit(String customerId) {

      System.out.println("Enter the amount to deposit:");
      double amount = sc.nextDouble();

        Customer_Account account = accounts.get(customerId);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            String transactionId = "TID" + transactionIdCounter++;
            String date = java.time.LocalDate.now().toString();
            String status = "Success";
            Statement statement = new Statement(transactionId, account.getAccountNo(), null, "Deposit", date, amount, status, String.valueOf(account.getBalance()));
            transactionHistory.add(statement);
            System.out.println("Deposit successful! New balance: " + account.getBalance());
        }   
        else {
            System.out.println("Account not found!");
        }
        
    }

    public void ATMwithdraw(String customerId) {
        System.out.println("Enter the amount to withdraw:");
        double amount = sc.nextDouble();    
        Customer_Account account = accounts.get(customerId);
        if (account != null) {
            if(account.getBalance() - amount <= 1000)
            {
                System.out.println("Minimum of 1000 should be in your account after withdrawal.");
                Statement statement = new Statement("TID" + transactionIdCounter++, account.getAccountNo(), null, "Withdrawal", java.time.LocalDate.now().toString(), amount, "Failed", String.valueOf(account.getBalance()));
                transactionHistory.add(statement);
                System.out.println("Withdrawal failed! Insufficient balance.");
            }
            else {
                account.setBalance(account.getBalance() - amount);
                Statement statement = new Statement("TID" + transactionIdCounter++, account.getAccountNo(), null, "Withdrawal", java.time.LocalDate.now().toString(), amount, "Success", String.valueOf(account.getBalance()));
                transactionHistory.add(statement);
                System.out.println("Withdrawal successful! New balance: " + account.getBalance());
            } 
        } else {
            System.out.println("Account not found!");
        }
    }

    public void transfer(String customerId) {
        System.out.println("Enter the receiver's account number:");
        String receiverAccountNo = sc.next();
        System.out.println("Enter the amount to transfer:");
        double amount = sc.nextDouble();    
        Customer_Account senderAccount = accounts.get(customerId);
        Customer_Account receiverAccount = null;
        for (Customer_Account account : accounts.values()) {
            if (account.getAccountNo().equals(receiverAccountNo)) {
                receiverAccount = account;
                break;
            }
        }
        if (senderAccount != null && receiverAccount != null) {
            if(senderAccount.getBalance() - amount <= 1000) {
                System.out.println("Minimum of 1000 should be in your account after transfer.");
                Statement statement = new Statement("TID" + transactionIdCounter++, senderAccount.getAccountNo(), receiverAccountNo, "Transfer", java.time.LocalDate.now().toString(), amount, "Failed", String.valueOf(senderAccount.getBalance()));
                transactionHistory.add(statement);
                System.out.println("Transfer failed! Insufficient balance.");
            } else {
                senderAccount.setBalance(senderAccount.getBalance() - amount);
                receiverAccount.setBalance(receiverAccount.getBalance() + amount);
                Statement statement = new Statement("TID" + transactionIdCounter++, senderAccount.getAccountNo(), receiverAccountNo, "Transfer", java.time.LocalDate.now().toString(), amount, "Success", String.valueOf(senderAccount.getBalance()));
                transactionHistory.add(statement);
                System.out.println("Transfer successful! New balance: " + senderAccount.getBalance());
            }   
        } else {
            System.out.println("Sender or receiver account not found!");
        }
    }

    public void viewBalance(String customerId) {
        Customer_Account account = accounts.get(customerId);
        if (account != null) {
            System.out.println("Current balance: " + account.getBalance());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void viewTransactionHistory(String customerId) {
        Customer_Account account = accounts.get(customerId);
        if (account != null) {
            System.out.println("Transaction History for Account No: " + account.getAccountNo());    
            for (Statement statement : transactionHistory) {
                if (statement.getAccountNo().equals(account.getAccountNo()) || statement.getReceiverAccountNo().equals(account.getAccountNo())) {
                    System.out.println(statement);
                }
            }
        }
        else {
            System.out.println("Account not found!");
        }
    }

    public void managePassword(String customerId) {
        Customer_Account account = accounts.get(customerId);
        if (account != null) {
            System.out.println("Enter your old password:");
            String oldPassword = sc.next();
            String encryptedOldPass = account.getPassword();
            StringBuilder decryptedOldPass = new StringBuilder();
            for (char c : encryptedOldPass.toCharArray()) {
                decryptedOldPass.append((char)(c - 1)); // Simple decryption logic for demonstration
            }
            if (!decryptedOldPass.toString().equals(oldPassword)) {
                System.out.println("Incorrect old password!");
                return;
            }
            System.out.println("Enter new password:");
            String newPassword = sc.next();
            StringBuilder encryptedPass = new StringBuilder();
            for (char c : newPassword.toCharArray()) {
                encryptedPass.append((char)(c + 1)); // Simple encryption logic for demonstration
            }
            account.setPassword(encryptedPass.toString());
            System.out.println("Password changed successfully!");
        }
        else {
            System.out.println("Account not found!");
        }
    
}

}
