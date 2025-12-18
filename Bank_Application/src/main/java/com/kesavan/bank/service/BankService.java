package com.kesavan.bank.service;

import com.kesavan.bank.exception.BankException;
import com.kesavan.bank.exception.InsufficientBalanceException;
import com.kesavan.bank.exception.InvalidAmountException;
import com.kesavan.bank.model.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing accounts and performing banking operations.
 */
public class BankService {

    private final Map<String, Account> accounts = new HashMap<>();

    /**
     * Creates a new bank account.
     *
     * @param accountNumber Unique account number
     * @param holderName    Name of the account holder
     * @param initialBalance Initial deposit amount
     * @throws BankException if account already exists or invalid data
     */
    public void createAccount(String accountNumber, String holderName, double initialBalance) throws BankException {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new BankException("Account number cannot be empty.");
        }
        if (holderName == null || holderName.trim().isEmpty()) {
            throw new BankException("Holder name cannot be empty.");
        }
        if (accounts.containsKey(accountNumber)) {
            throw new BankException("Account with number " + accountNumber + " already exists.");
        }
        if (initialBalance < 0) {
            throw new InvalidAmountException("Initial balance cannot be negative.");
        }

        Account newAccount = new Account(accountNumber, holderName, initialBalance);
        accounts.put(accountNumber, newAccount);
    }

    /**
     * Retrieves an account by its number.
     *
     * @param accountNumber The account number to search for
     * @return The Account object
     * @throws BankException if account not found
     */
    public Account getAccount(String accountNumber) throws BankException {
        if (!accounts.containsKey(accountNumber)) {
            throw new BankException("Account not found: " + accountNumber);
        }
        return accounts.get(accountNumber);
    }

    /**
     * Deposits amount into an account.
     * Synchronized to prevent race conditions during concurrent updates.
     *
     * @param accountNumber Content target account number
     * @param amount        Amount to deposit
     * @throws BankException if account not found or invalid amount
     */
    public synchronized void deposit(String accountNumber, double amount) throws BankException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }
        
        Account account = getAccount(accountNumber);
        // Critical section: Reading and updating balance must be atomic
        account.setBalance(account.getBalance() + amount);
    }

    /**
     * Withdraws amount from an account.
     * Synchronized to prevent race conditions during concurrent updates
     * and ensure balance check and update happen atomically.
     *
     * @param accountNumber Target account number
     * @param amount        Amount to withdraw
     * @throws BankException if account not found, invalid amount, or insufficient funds
     */
    public synchronized void withdraw(String accountNumber, double amount) throws BankException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        Account account = getAccount(accountNumber);
        
        // Critical section: Check balance and update must be atomic to avoid overdrawing
        if (account.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in account: " + accountNumber);
        }

        account.setBalance(account.getBalance() - amount);
    }
}
