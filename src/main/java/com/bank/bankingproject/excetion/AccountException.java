package com.bank.bankingproject.excetion;

public class AccountException extends RuntimeException {
    public AccountException(String message) {
        super(message);
    }
}
