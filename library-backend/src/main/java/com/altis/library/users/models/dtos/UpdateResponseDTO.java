package com.altis.library.users.models.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateResponseDTO(
        Long id,
        String nameFull,
        String phone,
        LocalDate dateBirth,
        String address,
        Boolean isAdmin,
        Boolean isDisable,
        LocalDateTime createDt,
        LocalDateTime updateDt


) {}
