package com.noceli.diego.wallet.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Transfer {
    private String senderFullName;
    private String receiverFullName;
    private BigDecimal transferAmount;

    public Transfer(String senderFullName, String receiverFullName, BigDecimal transferAmount) {
        this.senderFullName = senderFullName;
        this.receiverFullName = receiverFullName;
        this.transferAmount = transferAmount;
    }
}
