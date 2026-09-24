package com.altis.library.books.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateRequestDTO(

        @NotBlank(message = "This field is required, please enter title book!")
        @Size(min = 3,max = 100, message = "The title must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The title must contain only uppercase or lowercase letters, including accents and spaces!"
        )String title,


        @NotNull(message = "This field is required, please enter the genre!")
        @Size(min = 3,max = 100, message = "The genre must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The genre must contain only uppercase or lowercase letters, including accents and spaces!"
        )String genre,

        @NotNull(message = "This field is required, please enter the number of pages!")
        @Min(value = 1, message = "The number of pages must be at least 1!"
        )Integer numberPages,

        @NotNull(message = "This field is required, please enter your quantity of books!")
        @Min(value = 1, message = "Quantity must be at least 1"
        )Integer quantity,

        @NotNull(message = "Publisher is required")
        @Positive(message = "Publisher ID must be greater than 0!"
        )Long publisherId



) {}
