package com.ebuka.bankingapi.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest implements Serializable {

    @JsonProperty("amount")
    @NotNull(message = "withdrawal amount cannot be null")
    @PositiveOrZero(message = "Balance must be zero or a positive number")
    private BigDecimal amount;

    @JsonProperty("acc_number")
    @NotNull(message = "account number cannot be null")
    private String accountNumber;

    @JsonProperty("narration")
    private String narration;

    @JsonProperty("currency_type")
    @NotNull(message = "currency type cannot be null")
    private String currencyType;
}
