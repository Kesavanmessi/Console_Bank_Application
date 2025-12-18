package com.kesavan.bank.service;

import com.kesavan.bank.exception.BankException;

/**
 * Task to perform deposit or withdrawal in a separate thread.
 */
public class TransactionTask implements Runnable {

    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    private final BankService bankService;
    private final String accountNumber;
    private final TransactionType type;
    private final double amount;

    public TransactionTask(BankService bankService, String accountNumber, TransactionType type, double amount) {
        this.bankService = bankService;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            if (type == TransactionType.DEPOSIT) {
                bankService.deposit(accountNumber, amount);
                System.out.println("Thread " + Thread.currentThread().getName() + " deposited " + amount + " to " + accountNumber);
            } else if (type == TransactionType.WITHDRAW) {
                bankService.withdraw(accountNumber, amount);
                System.out.println("Thread " + Thread.currentThread().getName() + " withdrew " + amount + " from " + accountNumber);
            }
        } catch (BankException e) {
            System.err.println("Thread " + Thread.currentThread().getName() + " failed: " + e.getMessage());
        }
    }
}
