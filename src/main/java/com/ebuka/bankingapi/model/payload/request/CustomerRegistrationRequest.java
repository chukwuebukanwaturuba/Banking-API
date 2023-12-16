package com.ebuka.bankingapi.model.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationRequest implements Serializable {

    @JsonProperty("first_name")
    @NotNull(message = "first name cannot be empty")
    private String firstName;

    @JsonProperty("last_name")
    @NotNull(message = "last name cannot be empty")
    private String lastName;

    @JsonProperty("phone_number")
    @NotNull(message = "phone number cannot be empty")
    private String phoneNumber;

    @JsonProperty("dob")
    @NotNull(message = "date of birth cannot be empty")
    private String dateOfBirth;

    @JsonProperty("email_address")
    @NotNull(message = "email address cannot be empty")
    private String emailAddress;

    @JsonProperty("password")
    @NotNull(message = "password cannot be empty")
    private String password;

    @JsonProperty("accountType")
    @NotNull(message = "account_type cannot be empty")
    private String accountType;

    @JsonProperty("currencyType")
    @NotNull(message = "currency_type cannot be empty")
    private String currencyType;
}
