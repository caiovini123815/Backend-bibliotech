package com.altis.library.books.models.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UpdateResponseDTO(

        Long id,
        String title,
        LocalDate publicationDate,
        String genre,
        Integer numberPages,
        Integer quantity,
        Long publisherId,
        LocalDateTime createDt,
        LocalDateTime updateDt
) {}
