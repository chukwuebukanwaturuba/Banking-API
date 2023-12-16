package com.ebuka.bankingapi.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
public class DepositRequest implements Serializable {

    @JsonProperty("amount")
    @NotNull
    private BigDecimal amount;

    @JsonProperty("acc_number")
    @NotNull
    private String accountNumber;

    @JsonProperty("currency")
    @NotNull
    private String currency;
}
