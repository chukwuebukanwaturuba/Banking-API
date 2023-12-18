package com.ebuka.bankingapi.model.payload.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountStatementResponse implements Serializable {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("narration")
    private String narration;

    @JsonProperty("transaction_type")
    private String transactionType;

    @JsonProperty("created_at")
    private String createdAt;
}
