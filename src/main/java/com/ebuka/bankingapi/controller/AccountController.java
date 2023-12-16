package com.ebuka.bankingapi.controller;

import com.ebuka.bankingapi.model.payload.request.AccountInfoRequest;
import com.ebuka.bankingapi.model.payload.request.DepositRequest;
import com.ebuka.bankingapi.model.payload.request.WithdrawalRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/v1/accounts",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;
    @PostMapping("/account/info")
    public ResponseEntity<BaseResponse<?>> getAccountInfo(@Valid @RequestBody AccountInfoRequest accountInfoRequest) {
        try {
            BaseResponse<?> br = accountService.getAccountInfo(accountInfoRequest);
            return ResponseEntity.ok(br);
        } catch (Exception e) {
            log.error("[ Error ]: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/account/balance")
    public ResponseEntity<BaseResponse<?>> getAccountBalance(@Valid @RequestBody AccountInfoRequest accountInfoRequest) {
        try {
            BaseResponse<?> br = accountService.getAccountBalance(accountInfoRequest);
            return ResponseEntity.ok(br);
        } catch (Exception e) {
            log.error("[ Error ]: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/account/deposit")
    public ResponseEntity<BaseResponse<?>> getAccountInfo(@Valid @RequestBody DepositRequest depositRequest) {
        try {
            BaseResponse<?> br = accountService.deposit(depositRequest);
            return ResponseEntity.ok(br);
        } catch (Exception e) {
            log.error("[ Error ]: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/account/withdraw")
    public ResponseEntity<BaseResponse<?>> withdraw(@Valid @RequestBody WithdrawalRequest withdrawalRequest) {
        try {
            BaseResponse<?> br = accountService.withdraw(withdrawalRequest);
            return ResponseEntity.ok(br);
        } catch (Exception e) {
            log.error("[ Error ]: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
