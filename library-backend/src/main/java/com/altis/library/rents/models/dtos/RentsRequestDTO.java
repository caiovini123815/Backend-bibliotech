package com.altis.library.rents.models.dtos;

import jakarta.validation.constraints.*;


public record RentsRequestDTO(

        @NotNull(message = "User is required")
        @Positive(message = "User ID must be greater than 0!"
        )Long userId,

        @NotNull(message = "Book is required")
        @Positive(message = "Book ID must be greater than 0!"
        )Long bookId





) {}
