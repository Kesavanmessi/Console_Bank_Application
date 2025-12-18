package com.kesavan.bank.exception;

/**
 * Thrown when a transaction amount is invalid (e.g., negative or zero).
 */
public class InvalidAmountException extends BankException {

    public InvalidAmountException(String message) {
        super(message);
    }
}
