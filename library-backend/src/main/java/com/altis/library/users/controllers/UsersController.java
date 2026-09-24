package com.altis.library.users.controllers;

import com.altis.library.users.models.dtos.UsersRequestDTO;
import com.altis.library.users.models.dtos.UsersResponseDTO;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<UsersResponseDTO> create(
            @Valid @RequestBody UsersRequestDTO userData) {

        UsersResponseDTO createdUser = usersService.create(userData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<users>> findAll() {

        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<users> findById(@PathVariable Long id) {

        return ResponseEntity.ok(usersService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<users> update(
            @PathVariable Long id,
            @RequestBody users userData
    ) {

        return ResponseEntity.ok(
                usersService.update(id, userData)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<users> partialUpdate(
            @PathVariable Long id,
            @RequestBody users userData
    ) {

        return ResponseEntity.ok(
                usersService.partialUpdate(id, userData)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        usersService.delete(id);

        return ResponseEntity.noContent().build();
    }
}