package com.noceli.diego.wallet.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Withdraw {
    private String walletId;
    private String fullName;
    private String documentNumber;
    private BigDecimal withdrawAmount;

    public Withdraw(String walletId, String fullName, String documentNumber, BigDecimal withdrawAmount) {
        this.walletId = walletId;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
        this.withdrawAmount = withdrawAmount;
    }
}
