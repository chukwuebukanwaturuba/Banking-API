package com.ebuka.bankingapi.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountStatementRequest implements Serializable {

    @JsonProperty("acc_number")
    @NotNull(message = "account number cannot be null")
    private String accountNumber;


    @JsonProperty("currency_type")
    @NotNull(message = "currency type cannot be null")
    private String currencyType;


    @JsonProperty("from")
    @NotNull(message = "start date period cannot be null")
    private String from;


    @JsonProperty("to")
    @NotNull(message = "end date period cannot be null")
    private String to;
}
