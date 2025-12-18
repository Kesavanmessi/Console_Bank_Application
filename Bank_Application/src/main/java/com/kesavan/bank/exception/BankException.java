package com.kesavan.bank.exception;

/**
 * Base exception for all bank-related errors.
 */
public class BankException extends Exception {
    
    public BankException(String message) {
        super(message);
    }

    public BankException(String message, Throwable cause) {
        super(message, cause);
    }
}
