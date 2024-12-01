package com.noceli.diego.wallet.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class WithdrawResponse {
    private String walletId;
    private String fullName;
    private String documentNumber;
    private BigDecimal withdrawAmount;

    public WithdrawResponse(String walletId, String fullName, String documentNumber, BigDecimal withdrawAmount) {
        this.walletId = walletId;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
        this.withdrawAmount = withdrawAmount;
    }
}
