package com.altis.library.users.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDTO(
        @JsonProperty("email")
        String email,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("access_token")
        String accessToken,

        @JsonIgnore
        String password



) { public LoginResponseDTO(String email, String accessToken) {
        this(email, "Bearer", accessToken, null);
    }
}
