package com.noceli.diego.wallet.model.entity;

import com.noceli.diego.wallet.model.request.WalletRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String documentNumber;


    public WalletEntity(WalletRequest walletRequest) {
        Random random = new Random();
        int randomNumber = random.nextInt(11);

        this.id = id;
        this.userId = "usr" + randomNumber;
        this.balance = BigDecimal.ZERO;
        this.surname = walletRequest.getSurname();
        this.documentNumber = walletRequest.getDocumentNumber();
        this.name = walletRequest.getName();
    }

}
