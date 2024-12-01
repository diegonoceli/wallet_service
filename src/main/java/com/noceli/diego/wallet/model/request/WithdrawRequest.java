package com.noceli.diego.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class WithdrawRequest {
    private BigDecimal withdrawalAmount;
}
