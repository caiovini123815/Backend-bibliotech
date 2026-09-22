package com.altis.library.auth.models.dtos;

public record LoginResponseDTO(
        Long id,
        String nameFull,
        String email,
        Boolean isAdmin,
        String token

) {}
