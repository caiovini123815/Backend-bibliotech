package com.altis.library.auth.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequestDTO(

        @NotBlank(message = "The password is required, please enter your password!")
        @Size(min = 8, max = 72, message = "The password must be between 5 and 30 characters long, please follow the rules!")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$",
                message = "The password must contain only letters and numbers, including at least one uppercase letter and one number, please follow the rules!"

        )String newPassword,

        @NotBlank(message = "Repeat password is required")
        String repeatPassword

) {
}