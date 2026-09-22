package com.altis.library.auth.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(


        @NotBlank(message = "The email address is mandatory, please enter your email!")
        @Email(message = "Invalid email format, please enter your email correctly!")
        @Size(min = 5, max = 100 ,message = "The email must be between 5 and 100 characters long, please follow the rules!")
        String email,

        @NotBlank(message = "The password is required, please enter your password!")
        String password

){}
