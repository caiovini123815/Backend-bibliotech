package com.altis.library.auth.controllers;

import com.altis.library.auth.models.dtos.ForgotPasswordRequestDTO;
import com.altis.library.auth.models.dtos.ResetPasswordRequestDTO;
import com.altis.library.auth.models.dtos.ResetPasswordResponseDTO;
import com.altis.library.users.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ForgotPasswordControllers {

    private final UsersService usersService;

    public ForgotPasswordControllers(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO data
    ) {

        usersService.forgotPassword(data);

        return ResponseEntity.ok(
                "User confirmed successfully"
        );
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResetPasswordResponseDTO> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO data
    ) {

        return ResponseEntity.ok(
                usersService.resetPassword(data)
        );
    }
}