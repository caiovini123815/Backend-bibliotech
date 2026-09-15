package com.altis.library.publishers.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PublishersRequestDTO(
        @NotBlank(message = "This field is required, please enter your full name!")
        @Size(min = 3,max = 100, message = "The name must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The name must contain only uppercase or lowercase letters, including accents and spaces!"
        )String name_publisher,

        @NotBlank(message = "This field is required, please enter your cnpj!")
        @Size(min = 14,max = 14, message = "The cnpj must be between 14 digits long, please follow the rules!")
        @Pattern(
                regexp = "^\\d{14}$",
                message = "The cnpj number mus e in the format ex: 12345678000199!"
        )String cnpj,

        @NotBlank(message = "The email address is mandatory, please enter your email!")
        @Email(message = "Invalid email format, please enter your email correctly!")
        @Size(min = 5, max = 100 ,message = "The email must be between 5 and 100 characters long, please follow the rules!")
        String email,


        @NotBlank(message = "This field is required, please enter your phone!")
        @Size(min = 14,max = 15,message = "Your phone number must contain 14 and 15 digits long, please enter your phone number correctly!")
        @Pattern(
                regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
                message = "The phone number must be in the format (00) 00000-0000!"
        )String phone_publisher,

        @NotBlank(message = "This field is required, please enter your website!")
        @Size(min = 20,max = 150,message = "Your website must contain 20 and 150 characterslong, please follow the rules!")
        @Pattern(
                regexp = "^https?://(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)+(/.*)?$",
                message = "The website must be in the format ex: https://www.editora.com.br!"
        )String website


) {}
