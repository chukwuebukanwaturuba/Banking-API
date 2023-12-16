package com.ebuka.bankingapi.service.impl;

import com.ebuka.bankingapi.exception.BadRequestException;
import com.ebuka.bankingapi.model.entity.Transaction;
import com.ebuka.bankingapi.model.payload.AccountStatementResponse;
import com.ebuka.bankingapi.model.payload.request.AccountStatementRequest;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.model.repository.TransactionRepository;
import com.ebuka.bankingapi.service.TransactionService;
import com.ebuka.bankingapi.utils.Constants;
import com.ebuka.bankingapi.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private  final TransactionRepository transactionRepository;

    @Override
    public BaseResponse<?> generateAccountStatement(AccountStatementRequest accountStatementRequest) {
        try {
            boolean accountExists = ValidationUtils.resolveAccountNumberExists(accountStatementRequest.getAccountNumber());
            if (accountExists) {
                var from = ValidationUtils.formatLocalDateTime(accountStatementRequest.getFrom());
                var to = ValidationUtils.formatLocalDateTime(accountStatementRequest.getTo());
                List<Transaction> transactions = transactionRepository.getTransactionsBetween(from, to);
                if (transactions.size() > 0) {
                    List<AccountStatementResponse> accountStatementResponses = transactions.stream().map(
                            transaction -> AccountStatementResponse.builder()
                                    .amount(transaction.getAmount())
                                    .narration(transaction.getNarration())
                                    .transactionType(String.valueOf(transaction.getType()))
                                    .createdAt(String.valueOf(transaction.getCreatedAt()))
                                    .build()).toList();

                    return BaseResponse.builder()
                            .message("account info fetched successfully")
                            .statusCode(HttpStatus.OK.value())
                            .data(accountStatementResponses)
                            .build();
                } else {
                    log.error(Constants.NO_TRANSACTIONS_FOUND);
                    throw new RuntimeException(Constants.NO_TRANSACTIONS_FOUND);
                }
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
}
