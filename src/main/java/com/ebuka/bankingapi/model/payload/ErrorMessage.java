package com.ebuka.bankingapi.model.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorMessage {

    private int statusCode;

    private String message;

    private LocalDateTime time;
}
