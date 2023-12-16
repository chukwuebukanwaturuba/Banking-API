package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.AccountCreationRequest;

public interface AccountService {
    void createAccount(AccountCreationRequest accountCreationRequest);
}
