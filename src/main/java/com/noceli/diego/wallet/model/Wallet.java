package com.noceli.diego.wallet.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Wallet {
    private String userId;
    private BigDecimal balance;
    private String name;
    private String surname;
    private String documentNumber;

    public Wallet(String userId, BigDecimal balance, String name, String surname, String documentNumber) {
        this.userId = userId;
        this.balance = balance;
        this.name = name;
        this.surname = surname;
        this.documentNumber = documentNumber;
    }
}
