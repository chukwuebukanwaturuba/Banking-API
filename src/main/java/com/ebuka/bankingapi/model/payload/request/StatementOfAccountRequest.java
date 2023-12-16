package com.ebuka.bankingapi.model.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatementOfAccountRequest implements Serializable {
    private String accountNumber;

    private String from;

    private String to;
}
