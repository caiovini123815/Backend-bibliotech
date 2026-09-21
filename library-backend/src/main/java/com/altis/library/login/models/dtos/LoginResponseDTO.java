package com.altis.library.login.models.dtos;

public record LoginResponseDTO(
        Long id,
        String name_Full,
        String email,
        Boolean isAdmin

) {}
