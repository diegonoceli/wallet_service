package com.noceli.diego.wallet.repository;

import com.noceli.diego.wallet.model.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

    Optional<WalletEntity> findByUserId(String userId);
}
