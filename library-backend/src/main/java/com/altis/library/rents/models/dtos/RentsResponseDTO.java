package com.altis.library.rents.models.dtos;

import com.altis.library.books.models.entities.books;
import com.altis.library.users.models.entities.users;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RentsResponseDTO(
        Long id,
        users user,
        books book,
        LocalDate rentDate,
        LocalDate returnPeriod,
        LocalDate returnDate,
        String status,
        LocalDateTime createDt,
        LocalDateTime updateDt



) {}
