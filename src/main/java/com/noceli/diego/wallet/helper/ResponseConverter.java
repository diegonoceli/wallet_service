package com.noceli.diego.wallet.helper;

import com.noceli.diego.wallet.model.Withdraw;
import com.noceli.diego.wallet.model.response.WithdrawResponse;
import com.noceli.diego.wallet.model.Deposit;
import com.noceli.diego.wallet.model.response.DepositResponse;
import com.noceli.diego.wallet.model.Balance;
import com.noceli.diego.wallet.model.response.BalanceResponse;
import com.noceli.diego.wallet.model.Transfer;
import com.noceli.diego.wallet.model.response.TransferResponse;
import com.noceli.diego.wallet.model.Wallet;
import com.noceli.diego.wallet.model.response.WalletResponse;

public class ResponseConverter {

    public static WithdrawResponse convertToResponse(Withdraw withdraw) {
        return new WithdrawResponse(
                withdraw.getWalletId(),
                withdraw.getFullName(),
                withdraw.getDocumentNumber(),
                withdraw.getWithdrawAmount()
        );
    }

    public static WalletResponse convertToResponse(Wallet wallet) {
        return new WalletResponse(
                wallet.getUserId(),
                wallet.getBalance(),
                wallet.getName(),
                wallet.getSurname(),
                wallet.getDocumentNumber());
    }

    public static DepositResponse convertToResponse(Deposit deposit) {
        return new DepositResponse(
                deposit.getWalletId(),
                deposit.getFullName(),
                deposit.getDocumentNumber(),
                deposit.getDepositedAmount()
        );
    }

    public static BalanceResponse convertToResponse(Balance balance) {
        return new BalanceResponse(balance.getBalance());
    }

    public static TransferResponse convertToResponse(Transfer transfer) {
        return new TransferResponse(
                transfer.getSenderFullName(),
                transfer.getReceiverFullName(),
                transfer.getTransferAmount()
        );
    }
    
    
}