package com.noceli.diego.wallet.controller;

import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.Wallet;
import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.request.WalletRequest;
import com.noceli.diego.wallet.model.request.DepositRequest;
import com.noceli.diego.wallet.model.request.TransferRequest;
import com.noceli.diego.wallet.model.request.WithdrawRequest;
import com.noceli.diego.wallet.model.response.*;
import com.noceli.diego.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.noceli.diego.wallet.helper.ResponseConverter.convertToResponse;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    private final WalletService walletService;
// TODO: Implement integration tests for WalletController class

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> createWallet(@RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletService.createWallet(walletRequest);
        return ResponseEntity.ok(convertToResponse(wallet));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String id) {
        BalanceResponse response= new BalanceResponse(walletService.getWallet(id).getBalance());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{walletId}/deposit")
    public ResponseEntity<DepositResponse> deposit(@PathVariable String walletId, @RequestBody DepositRequest depositRequest) {
        Deposit deposit=walletService.deposit(walletId, depositRequest.getDepositAmount());
        return ResponseEntity.ok(convertToResponse(deposit));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<WithdrawResponse> withdraw(@PathVariable String id, @RequestBody WithdrawRequest withdrawRequest) {
        Withdraw withdraw=walletService.withdraw(id, withdrawRequest.getWithdrawalAmount());
        return ResponseEntity.ok(convertToResponse(withdraw));
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest transferRequest) {
        Transfer transfer = walletService.transfer(
                transferRequest.getSenderWalletId(),
                transferRequest.getReceiverWalletId(),
                transferRequest.getAmount());
        return ResponseEntity.ok(convertToResponse(transfer));
    }
}
