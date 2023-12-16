package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.AccountCreationRequest;
import com.ebuka.bankingapi.model.payload.request.AccountInfoRequest;
import com.ebuka.bankingapi.model.payload.request.DepositRequest;
import com.ebuka.bankingapi.model.payload.response.AccountInfoResponse;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;

public interface AccountService {
    void createAccount(AccountCreationRequest accountCreationRequest);

    BaseResponse<?> deposit(DepositRequest depositRequest);

    BaseResponse<?> getAccountBalance(AccountInfoRequest accountInfoRequest);

    BaseResponse<?> getAccountInfo(AccountInfoRequest accountInfoRequest);
}
