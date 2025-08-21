package model;

public class Customer_Account {
    
    private final String customerId;
    private final String account_no;
    private String accountHolderName;
    private double balance; 
    private String password;

    public Customer_Account(String customerId, String account_no, String accountHolderName, String password) {
        this.customerId = customerId;
        this.account_no = account_no;
        this.accountHolderName = accountHolderName;
        this.balance = 10000;
        this.password = password;
    }
    public String getCustomerId() {
        return customerId;
    }               
    public String getAccountNo() {
        return account_no;
    }   
    public String getAccountHolderName() {
        return accountHolderName;
    }   
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Customer ID: " + customerId + ", Account No: " + account_no + ", Account Holder Name: " + accountHolderName + ", Balance: " + balance;
    }
    public void addStatement(Statement statement) {
    
}
}
