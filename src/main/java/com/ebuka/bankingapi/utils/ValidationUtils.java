package com.ebuka.bankingapi.utils;

import com.ebuka.bankingapi.model.repository.CustomerRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;

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

    public static boolean resolveUserExists(String emailAddress) {
        return BeanContextAccessor.getBean("customerRepository" , CustomerRepository.class).existsByEmail(emailAddress);
    }
}
