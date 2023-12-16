package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.*;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;

public interface CustomerService {
    BaseResponse<?> registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);

    BaseResponse<?> deposit(DepositRequest depositRequest);

    BaseResponse<?> withdraw(WithdrawalRequest withdrawalRequest);

    BaseResponse<?> generateStatementOfAccount(StatementOfAccountRequest statementOfAccountRequest);

    BaseResponse<?> checkBalance(AccountInfoRequest accountInfoRequest);
}
