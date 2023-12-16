package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.AccountStatementRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;

public interface TransactionService {
    BaseResponse<?> generateAccountStatement(AccountStatementRequest accountStatementRequest);
}
