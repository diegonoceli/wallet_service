package com.noceli.diego.wallet.service;

import com.noceli.diego.wallet.exception.InsufficientBalanceException;
import com.noceli.diego.wallet.exception.WalletNotFoundException;
import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.entity.TransactionEntity;
import com.noceli.diego.wallet.model.entity.WalletEntity;
import com.noceli.diego.wallet.model.request.WalletRequest;
import com.noceli.diego.wallet.repository.TransactionRepository;
import com.noceli.diego.wallet.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WalletServiceTest {

    @InjectMocks
    private WalletService walletService;

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWallet_ShouldReturnCreatedWallet() {
        WalletRequest request = new WalletRequest();
        request.setName("John");
        request.setSurname("Doe");
        request.setDocumentNumber("123456789");

        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUserId("usr123");
        walletEntity.setName("John");
        walletEntity.setSurname("Doe");
        walletEntity.setDocumentNumber("123456789");
        walletEntity.setBalance(BigDecimal.ZERO);

        when(walletRepository.save(any(WalletEntity.class))).thenReturn(walletEntity);

        var result = walletService.createWallet(request);

        assertNotNull(result);
        assertEquals("usr123", result.getUserId());
        verify(walletRepository, times(1)).save(any(WalletEntity.class));
    }

    @Test
    void getWallet_ShouldReturnWallet_WhenWalletExists() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUserId("usr123");
        walletEntity.setBalance(BigDecimal.TEN);

        when(walletRepository.findByUserId("usr123")).thenReturn(Optional.of(walletEntity));

        var result = walletService.getWallet("usr123");

        assertNotNull(result);
        assertEquals("usr123", result.getUserId());
        verify(walletRepository, times(1)).findByUserId("usr123");
    }

    @Test
    void getWallet_ShouldThrowException_WhenWalletNotFound() {
        when(walletRepository.findByUserId("usr123")).thenReturn(Optional.empty());

        assertThrows(WalletNotFoundException.class, () -> walletService.getWallet("usr123"));
    }

    @Test
    void deposit_ShouldReturnDepositDetails() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUserId("usr123");
        walletEntity.setBalance(BigDecimal.ZERO);
        walletEntity.setName("John");
        walletEntity.setSurname("Doe");

        when(walletRepository.findByUserId("usr123")).thenReturn(Optional.of(walletEntity));
        when(walletRepository.save(any(WalletEntity.class))).thenReturn(walletEntity);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(new TransactionEntity());

        Deposit result = walletService.deposit("usr123", BigDecimal.TEN);

        assertNotNull(result);
        assertEquals("usr123", result.getWalletId());
        assertEquals(BigDecimal.TEN, result.getDepositedAmount());
        verify(walletRepository, times(1)).findByUserId("usr123");
        verify(walletRepository, times(1)).save(any(WalletEntity.class));
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void withdraw_ShouldReturnWithdrawDetails() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUserId("usr123");
        walletEntity.setBalance(BigDecimal.TEN);
        walletEntity.setName("John");
        walletEntity.setSurname("Doe");

        when(walletRepository.findByUserId("usr123")).thenReturn(Optional.of(walletEntity));
        when(walletRepository.save(any(WalletEntity.class))).thenReturn(walletEntity);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(new TransactionEntity());

        Withdraw result = walletService.withdraw("usr123", new BigDecimal("8.0"));

        assertNotNull(result);
        assertEquals("usr123", result.getWalletId());
        assertEquals(new BigDecimal("2.0"), result.getWithdrawAmount());
        verify(walletRepository, times(1)).findByUserId("usr123");
        verify(walletRepository, times(1)).save(any(WalletEntity.class));
        verify(transactionRepository, times(1)).save(any(TransactionEntity.class));
    }

    @Test
    void withdraw_ShouldThrowException_WhenInsufficientBalance() {
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setUserId("usr123");
        walletEntity.setBalance(BigDecimal.ONE);

        when(walletRepository.findByUserId("usr123")).thenReturn(Optional.of(walletEntity));

        assertThrows(InsufficientBalanceException.class, () -> walletService.withdraw("usr123", BigDecimal.TEN));
    }

    @Test
    void transfer_ShouldExecuteSuccessfully() {
        WalletEntity fromWallet = new WalletEntity();
        fromWallet.setUserId("from123");
        fromWallet.setBalance(BigDecimal.TEN);
        fromWallet.setName("John");
        fromWallet.setSurname("Doe");

        WalletEntity toWallet = new WalletEntity();
        toWallet.setUserId("to123");
        toWallet.setBalance(BigDecimal.ZERO);
        toWallet.setName("Jane");
        toWallet.setSurname("Smith");

        when(walletRepository.findByUserId("from123")).thenReturn(Optional.of(fromWallet));
        when(walletRepository.findByUserId("to123")).thenReturn(Optional.of(toWallet));
        when(walletRepository.save(any(WalletEntity.class))).thenReturn(fromWallet).thenReturn(toWallet);
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(new TransactionEntity());

        Transfer result = walletService.transfer("from123", "to123", BigDecimal.TEN);

        assertNotNull(result);
        assertEquals("John Doe", result.getSenderFullName());
        assertEquals("Jane Smith", result.getReceiverFullName());
        assertEquals(BigDecimal.TEN, result.getTransferAmount());
        verify(walletRepository, times(4)).findByUserId(anyString());
        verify(walletRepository, times(2)).save(any(WalletEntity.class));
    }
}