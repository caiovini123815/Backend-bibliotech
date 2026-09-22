package com.altis.library.auth.controllers;

import com.altis.library.auth.models.dtos.LoginRequestDTO;
import com.altis.library.auth.models.dtos.LoginResponseDTO;
import com.altis.library.auth.services.LoginServices;
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
        System.out.println("ENTROU NO POST /login");

        LoginResponseDTO response = loginServices.login(loginData);

        return ResponseEntity.ok(response);
    }
}