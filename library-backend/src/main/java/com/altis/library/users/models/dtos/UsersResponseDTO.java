package com.altis.library.users.models.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsersResponseDTO(
        Long id,
        String nameFull,
        String email,
        String cpf,
        String phone,
        LocalDate dateBirth,
        String address,
        String isAdmin,
        String isDisable,
        LocalDateTime createDt,
        LocalDateTime updateDt

) {}
