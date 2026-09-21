package com.altis.library.books.models.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record BooksResponseDTO(
        Long id,
        String title,
        String isbn,
        LocalDate publicationDate,
        String genre,
        Integer numberPages,
        Integer quantity,
        Long publisherId,
        LocalDateTime createDt,
        LocalDateTime updateDt


) {}
