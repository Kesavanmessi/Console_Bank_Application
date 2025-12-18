package com.kesavan.bank.app;

import com.kesavan.bank.exception.BankException;
import com.kesavan.bank.model.Account;
import com.kesavan.bank.service.BankService;
import com.kesavan.bank.service.TransactionTask;
import com.kesavan.bank.service.TransactionTask.TransactionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point for the Bank Application.
 * Demonstrates thread-safety and concurrent transaction handling.
 */
public class BankApplication {

    public static void main(String[] args) {
        System.out.println("Starting Bank Application...");

        BankService bankService = new BankService();
        String accountNumber = "ACC-001";

        try {
            // 1. Create sample account
            System.out.println("Creating account: " + accountNumber);
            bankService.createAccount(accountNumber, "Kesavan", 1000.00);
            System.out.println("Initial Balance: " + bankService.getAccount(accountNumber).getBalance());

            // 2. Prepare concurrent transactions
            List<Thread> threads = new ArrayList<>();
            
            // 5 threads depositing 100 each = +500
            for (int i = 0; i < 5; i++) {
                threads.add(new Thread(new TransactionTask(bankService, accountNumber, TransactionType.DEPOSIT, 100.00), "Deposit-Thread-" + i));
            }

            // 5 threads withdrawing 50 each = -250
            for (int i = 0; i < 5; i++) {
                threads.add(new Thread(new TransactionTask(bankService, accountNumber, TransactionType.WITHDRAW, 50.00), "Withdraw-Thread-" + i));
            }

            // 3. Start threads
            System.out.println("Starting concurrent transactions...");
            for (Thread thread : threads) {
                thread.start();
            }

            // 4. Wait for completion
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }

            // 5. Verify final balance (1000 + 500 - 250 = 1250)
            Account account = bankService.getAccount(accountNumber);
            System.out.println("All transactions completed.");
            System.out.println("Final Balance: " + account.getBalance());

        } catch (BankException e) {
            System.err.println("Application Error: " + e.getMessage());
        }
    }
}
