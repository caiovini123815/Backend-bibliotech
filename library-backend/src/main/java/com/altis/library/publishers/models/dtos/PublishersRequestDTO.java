package com.altis.library.publishers.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PublishersRequestDTO(

        @NotBlank(message = "This field is required, please enter the publisher name!")
        @Size(min = 3, max = 100, message = "The publisher name must be between 3 and 100 characters long!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The publisher name must contain only letters, accents and spaces!"
        ) String namePublisher,


        @NotBlank(message = "This field is required, please enter the CNPJ!")
        @Size(min = 14, max = 14, message = "The CNPJ must contain exactly 14 digits!")
        @Pattern(
                regexp = "^\\d{14}$",
                message = "The CNPJ must contain only numbers, for example: 12345678000199!"
        )String cnpj,


        @NotBlank(message = "This field is required, please enter the email!")
        @Email(message = "Invalid email format, please enter the email correctly!")
        @Size(min = 5, max = 100, message = "The email must be between 5 and 100 characters long!")
        String email,


        @NotBlank(message = "This field is required, please enter the phone number!")
        @Size(min = 14, max = 15, message = "The phone number must be between 14 and 15 characters long!")
        @Pattern(
                regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
                message = "The phone number must be in the format (00) 00000-0000!"
        )String phonePublisher,


        @NotBlank(message = "This field is required, please enter the website!")
        @Size(min = 20, max = 150, message = "The website must be between 20 and 150 characters long!")
        @Pattern(
                regexp = "^https?://(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+(/.*)?$",
                message = "The website must be in the format https://www.editora.com.br!"
        )String website

) {}