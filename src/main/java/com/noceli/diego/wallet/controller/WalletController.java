package com.noceli.diego.wallet.controller;

import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.request.DepositRequest;
import com.noceli.diego.wallet.model.request.TransferRequest;
import com.noceli.diego.wallet.model.request.WalletRequest;
import com.noceli.diego.wallet.model.request.WithdrawRequest;
import com.noceli.diego.wallet.model.response.*;
import com.noceli.diego.wallet.service.WalletService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.noceli.diego.wallet.helper.ResponseConverter.convertToResponse;

@RestController
@RequestMapping(WalletController.BASE_URI)
public class WalletController {

    private final WalletService walletService;
    static final String BASE_URI = "/wallets";
    private static final String STANDARD_JWT = "Bearer eyierei";

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    private boolean isValidToken(String token) {
        return STANDARD_JWT.equals(token);
    }

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(
            @RequestBody WalletRequest walletRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!isValidToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(convertToResponse(walletService.createWallet(walletRequest)));
    }

    @GetMapping("/{walletId}/balance")
    public ResponseEntity<BalanceResponse> getBalance(
            @PathVariable String walletId,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!isValidToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new BalanceResponse(walletService.getWalletById(walletId).getBalance()));
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<DepositResponse> deposit(
            @PathVariable String walletId,
            @RequestBody DepositRequest depositRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!isValidToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Deposit deposit = walletService.deposit(walletId, depositRequest.getDepositAmount());
        return ResponseEntity.ok(convertToResponse(deposit));
    }

    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<WithdrawResponse> withdraw(
            @PathVariable String walletId,
            @RequestBody WithdrawRequest withdrawRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!isValidToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Withdraw withdrawal = walletService.withdraw(walletId, withdrawRequest.getWithdrawalAmount());
        return ResponseEntity.ok(convertToResponse(withdrawal));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(
            @RequestBody TransferRequest transferRequest,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        if (!isValidToken(authorizationHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Transfer transfer = walletService.transfer(
                transferRequest.getSenderWalletId(),
                transferRequest.getReceiverWalletId(),
                transferRequest.getAmount());
        return ResponseEntity.ok(convertToResponse(transfer));
    }
}