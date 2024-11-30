package com.noceli.diego.wallet.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Deposit {
    private String walletId;
    private String fullName;
    private String documentNumber;
    private BigDecimal depositedAmount;

    public Deposit(String walletId, String fullName, String documentNumber, BigDecimal depositedAmount) {
        this.walletId = walletId;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
        this.depositedAmount = depositedAmount;
    }
}
