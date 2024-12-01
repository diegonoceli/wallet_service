package com.noceli.diego.wallet.service;

import com.noceli.diego.wallet.exception.InsufficientBalanceException;
import com.noceli.diego.wallet.exception.WalletNotFoundException;
import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.Wallet;
import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.entity.TransactionEntity;
import com.noceli.diego.wallet.model.entity.WalletEntity;
import com.noceli.diego.wallet.model.enums.TransactionType;
import com.noceli.diego.wallet.model.request.WalletRequest;
import com.noceli.diego.wallet.repository.TransactionRepository;
import com.noceli.diego.wallet.repository.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletService(WalletRepository walletRepository, TransactionRepository transactionRepository) {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public Wallet createWallet(WalletRequest walletRequest) {
        WalletEntity walletEntity = new WalletEntity(walletRequest);
        walletEntity = walletRepository.save(walletEntity);
        return convertToWallet(walletEntity);
    }

    public WalletEntity getWalletById(String walletId) {
        return walletRepository.findByUserId(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));
    }

    @Transactional
    public Deposit deposit(String walletId, BigDecimal amount) {
        WalletEntity wallet = getWalletById(walletId);
        updateWalletBalance(wallet, wallet.getBalance().add(amount));
        saveTransaction(wallet, TransactionType.DEPOSIT, amount);
        return new Deposit(wallet.getUserId(), getFullName(wallet), wallet.getDocumentNumber(), amount);
    }

    @Transactional
    public Withdraw withdraw(String walletId, BigDecimal amount) {
        WalletEntity wallet = getWalletById(walletId);
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException(walletId);
        }
        updateWalletBalance(wallet, wallet.getBalance().subtract(amount));
        saveTransaction(wallet, TransactionType.WITHDRAWAL, amount);
        return new Withdraw(walletId, getFullName(wallet), wallet.getDocumentNumber(), wallet.getBalance());
    }

    @Transactional
    public Transfer transfer(String fromWalletId, String toWalletId, BigDecimal amount) {
        withdraw(fromWalletId, amount);
        deposit(toWalletId, amount);
        WalletEntity fromWallet = getWalletById(fromWalletId);
        WalletEntity toWallet = getWalletById(toWalletId);
        return new Transfer(getFullName(fromWallet), getFullName(toWallet), amount);
    }

    private String getFullName(WalletEntity wallet) {
        return wallet.getName() + " " + wallet.getSurname();
    }

    private Wallet convertToWallet(WalletEntity walletEntity) {
        return new Wallet(
                walletEntity.getUserId(),
                walletEntity.getBalance(),
                walletEntity.getName(),
                walletEntity.getSurname(),
                walletEntity.getDocumentNumber()
        );
    }

    private void updateWalletBalance(WalletEntity wallet, BigDecimal newBalance) {
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);
    }

    private void saveTransaction(WalletEntity wallet, TransactionType type, BigDecimal amount) {
        transactionRepository.save(new TransactionEntity(wallet, type, amount, LocalDateTime.now()));
    }
}