package com.altis.library.users.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(

    @NotBlank(message = "The email address is mandatory, please enter your email!")
    @Email(message = "Invalid email format, please enter your email correctly!")
    @Size(min = 5, max = 100 ,message = "The email must be between 5 and 150 characters long, please follow the rules")
    String email,

    @NotBlank(message = "The password is required, please enter your password")
    @Size(min = 10, max = 30, message = "The password must be between 5 and 30 characters long, please follow the rules")
    @Pattern(
            regexp = "^(?=.*[A-Z]) (?=.*\\d)[a-zA-Z\\d]+$",
            message = "The password must contain only letters and numbers, including at least one uppercase letter and one number, please follow the rules"

    )String password

){}
