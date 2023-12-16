package com.ebuka.bankingapi.model.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class AuthenticationResponse implements Serializable {

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("time")
    private LocalDateTime time;
}
