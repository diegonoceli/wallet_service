package com.noceli.diego.wallet.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletRequest {
    private String name;
    private String surname;
    private String documentNumber;

    public WalletRequest(String name, String surname, String documentNumber) {
        this.name = name;
        this.surname = surname;
        this.documentNumber = documentNumber;
    }
}
