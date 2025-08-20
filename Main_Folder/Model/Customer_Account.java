package Model;

public class Customer_Account {
    
    private String customerId;
    private String account_no;
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
    
}
