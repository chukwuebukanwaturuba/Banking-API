package com.ebuka.bankingapi.service.impl;

import com.ebuka.bankingapi.exception.BadRequestException;
import com.ebuka.bankingapi.exception.InsufficientBalanceException;
import com.ebuka.bankingapi.model.entity.Account;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import com.ebuka.bankingapi.model.payload.request.*;
import com.ebuka.bankingapi.model.payload.response.AccountInfoResponse;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.model.payload.response.DepositResponse;
import com.ebuka.bankingapi.model.repository.AccountRepository;
import com.ebuka.bankingapi.service.AccountService;
import com.ebuka.bankingapi.utils.Constants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void createAccount(AccountCreationRequest accountCreationRequest) {
        if (!Objects.isNull(accountCreationRequest)) {
            Account account = new Account();
            account.setAccountNumber(accountCreationRequest.getAccountNumber());
            account.setAccountType(accountCreationRequest.getAccountType());
            account.setCurrencyType(accountCreationRequest.getCurrencyType());
            account.setUser(accountCreationRequest.getUser());
            accountRepository.save(account);
        } else {
            log.error(Constants.BAD_REQUEST);
            throw new BadRequestException(Constants.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public BaseResponse<?> deposit(DepositRequest depositRequest) {
        try {
            if (Objects.isNull(depositRequest)) {
                log.error(Constants.BAD_REQUEST);
                throw new BadRequestException(Constants.BAD_REQUEST);
            }
            Optional<Account> accountOptional = accountRepository.findAccountByAccountNumberAndCurrencyType(depositRequest.getAccountNumber(), CurrencyType.valueOf(depositRequest.getCurrencyType()));
            if (accountOptional.isPresent()) {
                var foundAccount = accountOptional.get();
                foundAccount.setBalance(foundAccount.getBalance().add(depositRequest.getAmount()));
                accountRepository.save(foundAccount);
            } else {
                log.error(Constants.ACCOUNT_DOES_NOT_EXIST);
                throw new BadRequestException(Constants.ACCOUNT_DOES_NOT_EXIST);
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .message("[ Error: ]" + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .build();
        }

        var depositDetails = DepositResponse.builder()
                .amount(depositRequest.getAmount())
                .currencyType(depositRequest.getCurrencyType())
                .accountNumber(depositRequest.getAccountNumber())
                .build();
        return BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully deposited into account")
                .data(depositDetails)
                .build();
    }

    @Override
    public BaseResponse<?> getAccountBalance(AccountInfoRequest accountInfoRequest) {
        try {
            if (Objects.isNull(accountInfoRequest)) {
                log.error(Constants.BAD_REQUEST);
                throw new BadRequestException(Constants.BAD_REQUEST);
            }
            Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(accountInfoRequest.getAccountNumber());
            if (accountOptional.isPresent()) {
                var foundAccount = accountOptional.get();
                var accountInfo = AccountInfoResponse.builder()
                        .balance(foundAccount.getBalance())
                        .accountCurrencyType(String.valueOf(foundAccount.getCurrencyType()))
                        .accountNumber(foundAccount.getAccountNumber())
                        .emailAddress(foundAccount.getUser().getEmail())
                        .build();


                return BaseResponse.builder()
                        .message("account info fetched successfully")
                        .statusCode(HttpStatus.OK.value())
                        .data(accountInfo)
                        .build();
            } else {
                log.info(Constants.ACCOUNT_DOES_NOT_EXIST);
                throw new BadRequestException(Constants.ACCOUNT_DOES_NOT_EXIST);
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .message("[ Error: ]" + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .build();
        }
    }

    @Override
    public BaseResponse<?> getAccountInfo(AccountInfoRequest accountInfoRequest) {
        try {
            Optional<Account> accountOptional = accountRepository.findAccountByAccountNumber(accountInfoRequest.getAccountNumber());
            if (accountOptional.isPresent()) {
                var foundAccount = accountOptional.get();
                var accountInfo = AccountInfoResponse.builder()
                        .accountNumber(foundAccount.getAccountNumber())
                        .balance(foundAccount.getBalance())
                        .firstName(foundAccount.getUser().getFirstname())
                        .lastName(foundAccount.getUser().getLastname())
                        .emailAddress(foundAccount.getUser().getEmail())
                        .accountType(String.valueOf(foundAccount.getAccountType()))
                        .accountCurrencyType(String.valueOf(foundAccount.getAccountType()))
                        .build();

                return BaseResponse.builder()
                        .message("account info fetched successfully")
                        .statusCode(HttpStatus.OK.value())
                        .data(accountInfo)
                        .build();
            } else {
                log.info(Constants.ACCOUNT_DOES_NOT_EXIST);
                throw new BadRequestException(Constants.ACCOUNT_DOES_NOT_EXIST);
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .message("[ Error: ]" + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .build();
        }
    }

    @Override
    @Transactional
    public BaseResponse<?> withdraw(WithdrawalRequest withdrawalRequest) {
        try {
            if (Objects.isNull(withdrawalRequest)) {
                log.error(Constants.BAD_REQUEST);
                throw new BadRequestException(Constants.BAD_REQUEST);
            }
            Optional<Account> accountOptional = accountRepository.findAccountByAccountNumberAndCurrencyType(withdrawalRequest.getAccountNumber(), CurrencyType.valueOf(withdrawalRequest.getCurrencyType()));
            if (accountOptional.isPresent()) {
                var foundAccount = accountOptional.get();
                if (foundAccount.getBalance().compareTo(withdrawalRequest.getAmount()) >= 0) {
                    foundAccount.setBalance(foundAccount.getBalance().subtract(withdrawalRequest.getAmount()));
                    accountRepository.save(foundAccount);
                    var depositDetails = DepositResponse.builder()
                            .amount(withdrawalRequest.getAmount())
                            .currencyType(withdrawalRequest.getCurrencyType())
                            .accountNumber(withdrawalRequest.getAccountNumber())
                            .build();
                    return BaseResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .message("Successfully deposited into account")
                            .data(depositDetails)
                            .build();
                } else {
                    log.error(Constants.INSUFFICIENT_BALANCE);
                    throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE);
                }

            } else {
                log.error(Constants.ACCOUNT_DOES_NOT_EXIST);
                throw new BadRequestException(Constants.ACCOUNT_DOES_NOT_EXIST);
            }
        } catch (Exception e) {
            return BaseResponse.builder()
                    .message("[ Error: ]" + e.getMessage())
                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .data(null)
                    .build();
        }
    }
}
