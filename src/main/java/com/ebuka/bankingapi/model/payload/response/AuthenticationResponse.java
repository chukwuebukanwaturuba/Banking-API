package com.ebuka.bankingapi.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("time")
    private LocalDateTime time;
}
