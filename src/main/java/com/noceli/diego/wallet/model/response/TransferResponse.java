package com.noceli.diego.wallet.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransferResponse {
    private String senderFullName;
    private String receiverFullName;
    private BigDecimal transferAmount;

    public TransferResponse(String senderFullName, String receiverFullName, BigDecimal transferAmount) {
        this.senderFullName = senderFullName;
        this.receiverFullName = receiverFullName;
        this.transferAmount = transferAmount;
    }
}
