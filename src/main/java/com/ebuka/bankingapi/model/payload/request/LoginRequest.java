package com.ebuka.bankingapi.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @JsonProperty("email_address")
    @NotNull(message = "email address cannot be empty")
    private String emailAddress;

    @JsonProperty("password")
    @NotNull(message = "password cannot be empty")
    private String password;
}
