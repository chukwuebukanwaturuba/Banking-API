package com.ebuka.bankingapi.model.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class DepositResponse implements Serializable {
    private BigDecimal amount;
    private String accountNumber;
    private String currencyType;
}
