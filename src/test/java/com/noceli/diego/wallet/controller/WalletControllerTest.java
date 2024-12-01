package com.noceli.diego.wallet.controller;

import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.Wallet;
import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.entity.WalletEntity;
import com.noceli.diego.wallet.model.request.DepositRequest;
import com.noceli.diego.wallet.model.request.TransferRequest;
import com.noceli.diego.wallet.model.request.WalletRequest;
import com.noceli.diego.wallet.model.request.WithdrawRequest;
import com.noceli.diego.wallet.model.response.*;
import com.noceli.diego.wallet.service.WalletService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WalletControllerTest {

    @Mock
    private WalletService walletService;

    @InjectMocks
    private WalletController walletController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    String authorization="Bearer eyierei";

    @Test
    void shouldCreateWalletSuccessfully() {
        WalletRequest request = new WalletRequest("John Doe", "USD", "documentNumber");
        Wallet wallet = new Wallet("1", BigDecimal.ZERO, "John Doe", "USD", "documentNumber");
        when(walletService.createWallet(request)).thenReturn(wallet);

        ResponseEntity<WalletResponse> response = walletController.createWallet(request,authorization);

        WalletResponse responseBody = response.getBody();

        assert responseBody != null;
        assertEquals("John Doe", responseBody.getName());
        assertEquals("USD", responseBody.getSurname());
        assertEquals("documentNumber", responseBody.getDocumentNumber());
        verify(walletService).createWallet(request);
    }

    @Test
    void shouldReturnCorrectBalanceForWallet() {
        String walletId = "1";

        WalletEntity wallet = new WalletEntity();
        wallet.setUserId(walletId);
        wallet.setBalance(new BigDecimal(500));
        wallet.setName("John");
        wallet.setSurname("Doe");
        wallet.setDocumentNumber("document");
        when(walletService.getWalletById(walletId)).thenReturn(wallet);

        ResponseEntity<BalanceResponse> response = walletController.getBalance(walletId,authorization);

        assertEquals(new BigDecimal(500), Objects.requireNonNull(response.getBody()).getBalance());
        verify(walletService).getWalletById(walletId);
    }

    @Test
    void shouldDepositAmountSuccessfully() {
        String walletId = "1";
        DepositRequest request = new DepositRequest(new BigDecimal(100));
        Deposit deposit = new Deposit(walletId, "full name", "documentNumber", new BigDecimal("100.0"));
        when(walletService.deposit(walletId, request.getDepositAmount())).thenReturn(deposit);

        ResponseEntity<DepositResponse> response = walletController.deposit(walletId, request,authorization);

        assertEquals("full name", Objects.requireNonNull(response.getBody()).getFullName());
        verify(walletService).deposit(walletId, request.getDepositAmount());
    }

    @Test
    void shouldWithdrawAmountSuccessfully() {
        String walletId = "1";
        WithdrawRequest request = new WithdrawRequest(new BigDecimal("50.0"));
        Withdraw withdraw = new Withdraw("walleId",
                "Full name",
                "documentNumber",
                new BigDecimal("30.0"));

        when(walletService.withdraw(walletId, request.getWithdrawalAmount())).thenReturn(withdraw);

        ResponseEntity<WithdrawResponse> response = walletController.withdraw(walletId, request,authorization);

        assertEquals("Full name", Objects.requireNonNull(response.getBody()).getFullName());
        verify(walletService).withdraw(walletId, request.getWithdrawalAmount());
    }

    @Test
    void shouldTransferAmountSuccessfully() {
        TransferRequest request = new TransferRequest("1", "2", new BigDecimal("200.0"));
        Transfer transfer = new Transfer("SenderFullName", "ReceiverFullName", new BigDecimal("200.0"));
        when(walletService.transfer(
                request.getSenderWalletId(),
                request.getReceiverWalletId(),
                request.getAmount())).thenReturn(transfer);

        TransferResponse response = walletController.transfer(request,authorization).getBody();

        assert response != null;
        assertEquals("SenderFullName", response.getSenderFullName());
        assertEquals("ReceiverFullName", response.getReceiverFullName());
        verify(walletService).transfer(
                request.getSenderWalletId(),
                request.getReceiverWalletId(),
                request.getAmount());
    }

    @Test
    void shouldFailToCreateWalletWithInvalidData() {
        WalletRequest invalidRequest = new WalletRequest("", "USD", "documentNumber");
        when(walletService.createWallet(invalidRequest)).thenThrow(new IllegalArgumentException("Invalid data"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            walletController.createWallet(invalidRequest,authorization);
        });

        assertEquals("Invalid data", exception.getMessage());
        verify(walletService).createWallet(invalidRequest);
    }
}