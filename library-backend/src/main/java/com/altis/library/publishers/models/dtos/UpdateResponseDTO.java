package com.altis.library.publishers.models.dtos;

import java.time.LocalDateTime;

public record UpdateResponseDTO(
        Long id,
        String name_publisher,
        String cnpj,
        String email,
        String phone_publisher,
        String website,
        LocalDateTime createDt,
        LocalDateTime updateDt

) {}
