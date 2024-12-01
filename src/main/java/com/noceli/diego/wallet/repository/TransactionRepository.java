package com.noceli.diego.wallet.repository;

import com.noceli.diego.wallet.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {}
