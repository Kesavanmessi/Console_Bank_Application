package com.kesavan.bank.util;

import com.kesavan.bank.model.Account;
import java.util.Comparator;

/**
 * Utility class providing comparators for sorting Accounts.
 */
public class AccountComparators {

    /**
     * Comparator to sort accounts by holder name alphabetical order.
     */
    public static final Comparator<Account> BY_HOLDER_NAME = Comparator.comparing(Account::getHolderName);

    /**
     * Comparator to sort accounts by balance in descending order (highest first).
     */
    public static final Comparator<Account> BY_BALANCE_DESC = Comparator.comparingDouble(Account::getBalance).reversed();

    // Prevent instantiation
    private AccountComparators() {}
}
