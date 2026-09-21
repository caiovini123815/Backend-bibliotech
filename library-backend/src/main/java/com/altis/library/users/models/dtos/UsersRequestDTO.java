package com.altis.library.users.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UsersRequestDTO(
        @NotBlank(message = "This field is required, please enter your full name!")
        @Size(min = 3,max = 100, message = "The name must be between 3 and 100 characters long, please follow the rules!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The name must contain only uppercase or lowercase letters, including accents and spaces!"
        )String name_full,

        @NotBlank(message = "The email address is mandatory, please enter your email!")
        @Email(message = "Invalid email format, please enter your email correctly!")
        @Size(min = 5, max = 150 ,message = "The email must be between 5 and 100 characters long, please follow the rules!")
        String email,

        @NotBlank(message = "This field is required, please enter your CPF!")
        @Size(min = 15,max = 15,message = "The cpf must contain 15 digits long,please follow the rules!")
        String cpf,

        @NotBlank(message = "This field is required, please enter your phone!")
        @Size(min = 14,max = 15,message = "Your phone number must contain 14 and 15 digits long, please enter your phone number correctly!")
        @Pattern(
                regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
                message = "The phone number must be in the format (00) 00000-0000!"
        )String phone,

        @NotNull(message = "This field is required, please enter your date of birth!")
        @Schema(
                description = "User date of birth in YYYY-MM-DD format",
                example = "2000-05-01"
        )LocalDate birthDate,

        @NotBlank(message = "This field is required, please enter your address correctly!")
        @Size(min = 10,max = 150,message = "Your address must contain 10 and 150 characters long, please enter your address correctly!")
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+,\\s\\d+\\s-\\s[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The address must be in the format: Street Name, Number - City Name!"
        )String address,

        @NotBlank(message = "The password is required, please enter your password!")
        @Size(min = 8, max = 72, message = "The password must be between 5 and 30 characters long, please follow the rules!")
        @Pattern(
                regexp = "^(?=.*[A-Z]) (?=.*\\d)[a-zA-Z\\d]+$",
                message = "The password must contain only letters and numbers, including at least one uppercase letter and one number, please follow the rules!"

        )String password

) {}
