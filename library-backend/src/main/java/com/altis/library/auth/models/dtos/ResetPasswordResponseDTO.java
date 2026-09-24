package com.altis.library.auth.models.dtos;

public record ResetPasswordResponseDTO(

        String message,
        String encryptedPassword

) {
}