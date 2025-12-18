package com.kesavan.bank.exception;

/**
 * Thrown when an account does not have enough funds for a transaction.
 */
public class InsufficientBalanceException extends BankException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
