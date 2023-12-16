package com.ebuka.bankingapi.service.impl;


import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.enums.AccountType;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import com.ebuka.bankingapi.model.enums.Role;
import com.ebuka.bankingapi.model.payload.request.AccountCreationRequest;
import com.ebuka.bankingapi.model.payload.request.CustomerRegistrationRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.model.repository.CustomerRepository;
import com.ebuka.bankingapi.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.ebuka.bankingapi.utils.EntityMapper.resolveToUserEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRegisterWhenCustomerDoesNotExist_ShouldRegisterCustomerSuccessfully() {
        CustomerRegistrationRequest cr = CustomerRegistrationRequest.builder()
                .firstName("ebuka")
                .lastName("Nwaturuba")
                .emailAddress("ebuka@gmail.com")
                .dateOfBirth("01-01-2022")
                .accountType(String.valueOf(AccountType.SAVINGS))
                .currencyType(String.valueOf(CurrencyType.NGN))
                .phoneNumber("+2347038559543")
                .password("1234@@")
                .build();

        var user = resolveToUserEntity(cr);
        user.setPassword(passwordEncoder.encode(cr.getPassword()));
        user.setRole(Role.ROLE_CUSTOMER);
        when(customerRepository.existsByEmail(cr.getEmailAddress())).thenReturn(false);
        when(customerRepository.save(any(User.class))).thenReturn(user);

        BaseResponse<?> br = customerService.registerCustomer(cr);

        assertEquals("Customer registered successfully!", br.getMessage());
        assertEquals(HttpStatus.OK.value(), br.getStatusCode());
        verify(accountService, times(1)).createAccount(any(AccountCreationRequest.class));
    }
}