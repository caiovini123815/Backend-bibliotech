package com.altis.library.books.models.dtos;

import jakarta.validation.constraints.*;


public record UpdateRequestDTO(

        @Size(min = 3,max = 100, message = "The title must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The title must contain only uppercase or lowercase letters, including accents and spaces!"
        )String title,


        @Size(min = 3,max = 100, message = "The genre must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The genre must contain only uppercase or lowercase letters, including accents and spaces!"
        )String genre,

        @Min(value = 1, message = "The number of pages must be at least 1!"
        )Integer numberPages,

        @Min(value = 1, message = "Quantity must be at least 1"
        )Integer quantity,

        @Positive(message = "Publisher ID must be greater than 0!"
        )Long publisherId


) {}