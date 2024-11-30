package com.noceli.diego.wallet.model.entity;

import com.noceli.diego.wallet.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", referencedColumnName = "id", nullable = false)
    private WalletEntity wallet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public TransactionEntity(WalletEntity wallet, TransactionType type, BigDecimal amount, LocalDateTime createdAt) {
        this.wallet = wallet;
        this.type = type;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
