package com.ebuka.bankingapi.service.impl;

import com.ebuka.bankingapi.exception.DuplicateUserException;
import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.enums.AccountType;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import com.ebuka.bankingapi.model.enums.Role;
import com.ebuka.bankingapi.model.payload.request.*;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.model.repository.CustomerRepository;
import com.ebuka.bankingapi.service.AccountService;
import com.ebuka.bankingapi.service.CustomerService;
import com.ebuka.bankingapi.utils.Constants;
import com.ebuka.bankingapi.utils.EntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountService accountService;


    @Override
    public BaseResponse<?> registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {
        try {
        boolean alreadyExists = resolveCustomerExists(customerRegistrationRequest.getEmailAddress());
        if (alreadyExists) {
            log.error("[ Customer onboarding error: ] {}", Constants.USER_ALREADY_EXISTS);
            throw new DuplicateUserException(Constants.USER_ALREADY_EXISTS);
        }


            User newUser = EntityMapper.resolveToUserEntity(customerRegistrationRequest);
            newUser.setPassword(passwordEncoder.encode(customerRegistrationRequest.getPassword()));
            newUser.setRole(Role.ROLE_CUSTOMER);

            User savedUser = customerRepository.save(newUser);

            AccountCreationRequest accountCreationRequest = AccountCreationRequest.builder()
                    .accountNumber(customerRegistrationRequest.getPhoneNumber())
                    .accountType(AccountType.valueOf(customerRegistrationRequest.getAccountType()))
                    .currencyType(CurrencyType.valueOf(customerRegistrationRequest.getCurrencyType()))
                    .user(savedUser)
                    .build();

            accountService.createAccount(accountCreationRequest);


            return BaseResponse.builder()
                    .message("Customer registered successfully!")
                    .statusCode(HttpStatus.OK.value())
                    .build();

        } catch (Exception ex) {
            return BaseResponse.builder()
                    .message("[ Error ]: " + ex.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();
        }
    }

    private boolean resolveCustomerExists(String emailAddress) {
        return customerRepository.existsByEmail(emailAddress);
    }
}

