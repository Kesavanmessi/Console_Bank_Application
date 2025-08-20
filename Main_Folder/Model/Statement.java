package Model;

public class Statement {
    private String transactionId; // For transfers
    private String account_no;
    private String receiverAccount_no; // For transfers
    private String type; // e.g., "Deposit", "Withdrawal"       
    private String date;
    private double amount;  
    private String status; 
    private String curBalance; 

    public Statement(String transactionId, String accountId, String receiverAccountId, String type, String date, double amount, String status, String curBalance) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.receiverAccountId = receiverAccountId;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.curBalance = curBalance;
    }   
    
}
