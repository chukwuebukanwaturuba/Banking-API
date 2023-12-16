package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.LoginRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;

public interface AuthenticationService {
    BaseResponse<?> authenticateCustomer(LoginRequest loginRequest);
}
