package com.ebuka.bankingapi.utils;

import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.payload.request.CustomerRegistrationRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class EntityMapper {

    public static User resolveToUserEntity(CustomerRegistrationRequest customerRegistrationRequest) {
        User newUser = new User();
        newUser.setFirstname(customerRegistrationRequest.getFirstName());
        newUser.setLastname(customerRegistrationRequest.getLastName());
        newUser.setEmail(customerRegistrationRequest.getEmailAddress());
        newUser.setDateOfBirth(formatDate(customerRegistrationRequest.getDateOfBirth()));
        newUser.setPhoneNumber(ValidationUtils.removeInternationalCode(customerRegistrationRequest.getPhoneNumber()));
        return newUser;
    }

    private static LocalDate formatDate(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dob, formatter);
    }
}
