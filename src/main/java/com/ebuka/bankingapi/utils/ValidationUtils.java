package com.ebuka.bankingapi.utils;

import com.ebuka.bankingapi.model.repository.CustomerRepository;
import com.ebuka.bankingapi.model.repository.TransactionRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ValidationUtils {
    public static Principal getAuthenticatedUser() {
        return (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String removeInternationalCode(String phoneNumber) {
        if (phoneNumber.startsWith("+234")) {
            return phoneNumber.substring(4);
        } else if (phoneNumber.startsWith("+")) {
            int firstDigitIndex = phoneNumber.indexOf(' ') + 1;
            return phoneNumber.substring(firstDigitIndex);
        }

        return phoneNumber;
    }

    public static boolean resolveAccountNumberExists(String accountNumber) {
        return BeanContextAccessor.getBean("transactionRepository" , TransactionRepository.class).existsByAccount_AccountNumber(accountNumber);
    }

    public static LocalDate formatDate(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dob, formatter);
    }

    public static LocalDateTime formatLocalDateTime(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.parse(dob, formatter);
    }
}
