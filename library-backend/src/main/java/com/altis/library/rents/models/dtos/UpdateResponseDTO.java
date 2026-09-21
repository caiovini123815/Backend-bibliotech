package com.altis.library.rents.models.dtos;

import java.time.LocalDateTime;

public record UpdateResponseDTO(

        Long id,
        Long userId,
        Long bookId,
        LocalDateTime rentDate,
        LocalDateTime returnPeriod,
        LocalDateTime returnDate,
        String status,
        LocalDateTime createDt,
        LocalDateTime updateDt

) {}
