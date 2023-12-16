package com.ebuka.bankingapi.model.payload.request;

import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.enums.AccountType;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AccountCreationRequest {
    private String accountNumber;

    private AccountType accountType;

    private CurrencyType currencyType;

    private User user;
}
