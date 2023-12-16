package com.ebuka.bankingapi.controller;

import com.ebuka.bankingapi.model.payload.request.AccountStatementRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.service.TransactionService;
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

@RequestMapping(value = "/api/v1/transactions",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;
    @PostMapping("/account/statement")
    public ResponseEntity<BaseResponse<?>> getAccountInfo(@Valid @RequestBody AccountStatementRequest accountStatementRequest) {
        try {
            BaseResponse<?> br = transactionService.generateAccountStatement(accountStatementRequest);
            return ResponseEntity.ok(br);
        } catch (Exception e) {
            log.error("[ Error ]: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
