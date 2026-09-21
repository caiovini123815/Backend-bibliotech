package com.altis.library.rents.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RentsRequestDTO(

        @NotNull(message = "User is required")
        @Positive(message = "User ID must be greater than 0!"
        )Long userId,

        @NotNull(message = "Book is required")
        @Positive(message = "Book ID must be greater than 0!"
        )Long bookId,


        @NotNull(message = "This field is required, please enter your rent date!")
        @Schema(
                description = "Rent date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate rentDate,

        @NotNull(message = "This field is required, please enter your return period date!")
        @Schema(
                description = "Return period date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate returnPeriod,

        @NotNull(message = "This field is required, please enter your return date!")
        @Schema(
                description = "Return date in YYYY-MM-DD format",
                example = "2026-09-20"
        )LocalDate returnDate,

        @NotBlank(message = "Status is required")
        @Pattern(
                regexp = "^(RENTED|RETURNED|LATE)$",
                message = "Status must be RENTED, RETURNED or LATE"
        )String status



) {}
