package com.altis.library.users.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginResponseDTO(
        Long id,
        String email

) {}
