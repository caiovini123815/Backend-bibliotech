package com.altis.library.rents.models.dtos;

import jakarta.validation.constraints.*;


public record UpdateRequestDTO(

        @Positive(message = "User ID must be greater than 0!"
        )Long userId,

        @Positive(message = "Book ID must be greater than 0!"
        )Long bookId




) {}
