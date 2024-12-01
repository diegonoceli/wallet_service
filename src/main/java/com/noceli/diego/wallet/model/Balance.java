package com.noceli.diego.wallet.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Balance {
    private BigDecimal balance;

    public Balance(BigDecimal balance) {
        this.balance = balance;
    }
}
