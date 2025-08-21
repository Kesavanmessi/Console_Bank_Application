package model;

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
        this.account_no = accountId;
        this.receiverAccount_no = receiverAccountId;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.status = status;
        this.curBalance = curBalance;
    }   
    public String getTransactionId() {
        return transactionId;
    }
    public String getAccountNo() {
        return account_no;
    }       
    public String getReceiverAccountNo() {
        return receiverAccount_no;
    }
    public String getType() {
        return type;
    }
    public String getDate() {
        return date;
    }
    public double getAmount() {
        return amount;
    }
    public String getStatus() {
        return status;
    }
    public String getCurBalance() {
        return curBalance;
    }
    @Override
    public String toString() {
        return "Transaction ID: " + transactionId + ", Account No: " + account_no + ", Receiver Account No: " + receiverAccount_no + ", Type: " + type + ", Date: " + date + ", Amount: " + amount + ", Status: " + status + ", Current Balance: " + curBalance;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public void setAccountNo(String account_no) {
        this.account_no = account_no;
    }
    public void setReceiverAccountNo(String receiverAccount_no) {   
        this.receiverAccount_no = receiverAccount_no;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setCurBalance(String curBalance) {
        this.curBalance = curBalance;
    }
    public void addStatement(Statement statement) {
        // This method can be used to add a statement to a customer's account
        // Implementation can be added later if needed
    }
    
}
