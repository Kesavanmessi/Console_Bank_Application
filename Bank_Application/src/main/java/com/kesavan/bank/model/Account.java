package com.kesavan.bank.model;

import java.util.Objects;

/**
 * Represents a bank account with basic details.
 * Implements Comparable to allow sorting by account number.
 */
public class Account implements Comparable<Account> {

    private String accountNumber;
    private String holderName;
    private double balance;

    /**
     * Constructs a new Account.
     *
     * @param accountNumber Unique identifier for the account
     * @param holderName    Name of the account holder
     * @param balance       Initial balance
     */
    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Account other) {
        if (other == null) {
            return 1; // Null is considered smaller, or handle as per strict requirements? 
                      // Standard practice: throw NPE or handle. 
                      // For this: standard String comparison.
            // Safe null check usually not required if generic contract is followed, 
            // but let's just delegate to String compareTo.
        }
        // Null safe comparison for Strings or assume non-null?
        // Assume accountNumber is non-null as per constructor.
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", holderName='" + holderName + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(accountNumber, account.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
