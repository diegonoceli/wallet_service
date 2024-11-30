package com.noceli.diego.wallet.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositResponse {
    private String walletId;
    private String fullName;
    private String documentNumber;
    private BigDecimal depositedAmount;

    public DepositResponse(String walletId, String fullName, String documentNumber, BigDecimal depositedAmount) {
        this.walletId = walletId;
        this.fullName = fullName;
        this.documentNumber = documentNumber;
        this.depositedAmount = depositedAmount;
    }
}
