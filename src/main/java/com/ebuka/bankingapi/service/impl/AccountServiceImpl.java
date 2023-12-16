package com.ebuka.bankingapi.service.impl;

import com.ebuka.bankingapi.model.entity.Account;
import com.ebuka.bankingapi.model.payload.request.AccountCreationRequest;
import com.ebuka.bankingapi.model.repository.AccountRepository;
import com.ebuka.bankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void createAccount(AccountCreationRequest accountCreationRequest) {
        Account account = new Account();
        account.setAccountNumber(accountCreationRequest.getAccountNumber());
        account.setAccountType(accountCreationRequest.getAccountType());
        account.setCurrencyType(accountCreationRequest.getCurrencyType());
        account.setUser(accountCreationRequest.getUser());
        accountRepository.save(account);
    }
}
