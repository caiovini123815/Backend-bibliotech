package com.altis.library.publishers.models.dtos;

public record UpdateResponseDTO(
        Long id,
        String name_publisher,
        String cnpj,
        String email,
        String phone_publisher,
        String website

) {}
