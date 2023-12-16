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
public class AccountInfoResponse implements Serializable {
    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("balance")
    private BigDecimal balance;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("account_ccy_type")
    private String accountCurrencyType;
}
