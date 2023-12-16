package com.ebuka.bankingapi.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class DepositResponse implements Serializable {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("currency_type")
    private String currencyType;
}
