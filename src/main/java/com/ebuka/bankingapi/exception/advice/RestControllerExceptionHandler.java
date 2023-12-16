package com.ebuka.bankingapi.exception.advice;

import com.ebuka.bankingapi.exception.BadRequestException;
import com.ebuka.bankingapi.exception.DuplicateUserException;
import com.ebuka.bankingapi.model.payload.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage badRequestException(BadRequestException badRequestException) {
        return ErrorMessage.builder()
                .message(badRequestException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage duplicateUserException(DuplicateUserException duplicateUserException) {
        return ErrorMessage.builder()
                .message(duplicateUserException.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .time(LocalDateTime.now())
                .build();
    }
}
