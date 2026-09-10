package com.altis.library.users.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdateRequestDTO(
        @NotBlank(message = "This field is required, please enter your full name!")
        @Size(min = 3,max = 100, message = "The name must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The name must contain only uppercase or lowercase letters, including accents and spaces!"
        )String name_full,

        @NotBlank(message = "This field is required, please enter your phone!")
        @Size(min = 14,max = 15,message = "Your phone number must contain 14 and 15 digits long, please enter your phone number correctly!")
        @Pattern(
                regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\{4}$",
                message = "The phone number must be in the format (00) 00000-0000!"
        )String phone,

        @NotBlank(message = "This field is required, please enter your date of birth!")
        @Size(min = 10, max = 10,message = "Your date of birth must contain 10 digits, please enter your date of birth correctly!")
        @Pattern(
                regexp = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[02])/\\d{4}$",
                message = "The date of birth must be in the format DD/MM/YYYY!"
        ) LocalDate date_Birth,

        @NotBlank(message = "This field is required, please enter your address correctly!")
        @Size(min = 10,max = 150,message = "Your address must contain 10 and 150 characters long, please enter your address correctly!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+,\\s\\d+\\s-\\s[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The address must be in the format: Street Name, Number - City Name!"
        )String address

) {}
