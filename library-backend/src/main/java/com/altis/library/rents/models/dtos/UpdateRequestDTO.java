package com.altis.library.rents.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateRequestDTO(

        @Positive(message = "User ID must be greater than 0!"
        )Long userId,

        @Positive(message = "Book ID must be greater than 0!"
        )Long bookId,


        @Schema(
                description = "Rent date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate rentDate,

        @Schema(
                description = "Return period date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate returnPeriod,

        @Schema(
                description = "Return date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate returnDate,

        @Pattern(
                regexp = "^(RENTED|RETURNED|LATE)$",
                message = "Status must be RENTED, RETURNED or LATE"
        )String status

) {}
