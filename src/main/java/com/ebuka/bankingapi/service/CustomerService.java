package com.ebuka.bankingapi.service;

import com.ebuka.bankingapi.model.payload.request.*;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;

public interface CustomerService {
    BaseResponse<?> registerCustomer(CustomerRegistrationRequest customerRegistrationRequest);
}
