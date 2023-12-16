package com.ebuka.bankingapi.service.impl;

import com.ebuka.bankingapi.engine.security.JwtService;
import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.payload.request.LoginRequest;
import com.ebuka.bankingapi.model.payload.response.AuthenticationResponse;
import com.ebuka.bankingapi.model.payload.response.BaseResponse;
import com.ebuka.bankingapi.model.repository.CustomerRepository;
import com.ebuka.bankingapi.service.AuthenticationService;
import com.ebuka.bankingapi.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public BaseResponse<?> authenticateCustomer(LoginRequest loginRequest) {
        String jwt;
        try {
            Optional<User> optionalUser = customerRepository.findUserByEmail(loginRequest.getEmailAddress());
            if (optionalUser.isPresent()) {
                var user = optionalUser.get();
                if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                    jwt = jwtService.generateToken(user);
                    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.error(Constants.WRONG_PASSWORD);
                    throw new UsernameNotFoundException(Constants.WRONG_PASSWORD);
                }
            } else {
                log.error(Constants.USER_DOES_NOT_EXIST);
                throw new UsernameNotFoundException(Constants.USER_DOES_NOT_EXIST);
            }

        } catch (Exception e) {
            log.error("[ authentication error: ] {}", e.getMessage());
            return BaseResponse.builder()
                    .message("authentication error")
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }

        var ar = AuthenticationResponse.builder()
                .token(jwt)
                .time(LocalDateTime.now())
                .build();

        return BaseResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("authentication successful")
                .data(ar)
                .build();
    }
}
