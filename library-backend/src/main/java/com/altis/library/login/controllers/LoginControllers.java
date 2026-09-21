package com.altis.library.login.controllers;

import com.altis.library.login.models.dtos.LoginRequestDTO;
import com.altis.library.login.models.dtos.LoginResponseDTO;
import com.altis.library.login.services.LoginServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginControllers {

    private final LoginServices loginServices;

    public LoginControllers(LoginServices loginServices) {
        this.loginServices = loginServices;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO loginData
    ) {

        LoginResponseDTO response = loginServices.login(loginData);

        return ResponseEntity.ok(response);
    }
}