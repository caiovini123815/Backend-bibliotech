package com.altis.library.auth.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterRequestDTO(

        @NotBlank(message = "This field is required, please enter your full name!")
        @Size(min = 3, max = 100)
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The name must contain only letters and spaces!"
        )
        String nameFull,

        @NotBlank(message = "Email is required!")
        @Email(message = "Invalid email format!")
        @Size(min = 5, max = 150)
        String email,

        @NotBlank(message = "CPF is required!")
        @Size(min = 14, max = 14)
        String cpf,

        @NotBlank(message = "Phone is required!")
        @Size(min = 14, max = 15)
        @Pattern(
                regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$",
                message = "The phone number must be in the format (00) 00000-0000!"
        )
        String phone,

        @NotNull(message = "Birth date is required!")
        @Schema(
                description = "User date of birth in YYYY-MM-DD format",
                example = "2000-05-01"
        )
        LocalDate birthDate,

        @NotBlank(message = "Address is required!")
        @Size(min = 10, max = 150)
        @Pattern(
                regexp = "^[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+,\\s\\d+\\s-\\s[a-zA-ZáàâãéêíóôõúçÁÀÂÃÉÊÍÓÔÕÚÇ\\s]+$",
                message = "The address must be in the format: Street Name, Number - City Name!"
        )
        String address,

        @NotBlank(message = "Password is required!")
        @Size(min = 8, max = 72)
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$",
                message = "The password must contain at least one uppercase letter and one number!"
        )
        String password

) {}