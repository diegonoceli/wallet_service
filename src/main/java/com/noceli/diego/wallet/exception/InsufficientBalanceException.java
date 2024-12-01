package com.noceli.diego.wallet.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String walletId) {
        super("Insufficient balance in wallet with ID: " + walletId);
    }
}
