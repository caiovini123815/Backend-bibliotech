package com.altis.library.auth.controllers;

import com.altis.library.auth.models.dtos.RegisterRequestDTO;
import com.altis.library.users.models.dtos.UsersResponseDTO;
import com.altis.library.users.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/register")
public class RegisterControllers {

    private final UsersService usersService;

    public RegisterControllers(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO userData
    ) {

        UsersResponseDTO createdUser =
                usersService.register(userData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(createdUser);
    }
}