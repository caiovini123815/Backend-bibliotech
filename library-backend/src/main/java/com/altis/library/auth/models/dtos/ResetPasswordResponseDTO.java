package com.altis.library.auth.models.dtos;

public record ResetPasswordResponseDTO(

        String Email,
        String cpf,
        String message,
        String encryptedPassword

) {
}