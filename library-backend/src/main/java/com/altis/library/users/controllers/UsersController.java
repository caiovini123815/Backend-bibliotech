package com.altis.library.users.controllers;

import com.altis.library.users.models.dtos.UsersRequestDTO;
import com.altis.library.users.models.dtos.UsersResponseDTO;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

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
    public ResponseEntity<Page<UsersResponseDTO>> findAll(
            Pageable pageable
    ) {

        return ResponseEntity.ok(
                usersService.findAll(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> findById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                usersService.findByIdResponse(id)
        );
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

    @PatchMapping("/{id}/disable")
    public ResponseEntity<UsersResponseDTO> disableUser(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                usersService.disableUser(id)
        );
    }

    @PatchMapping("/{id}/enable")
    public ResponseEntity<UsersResponseDTO> enableUser(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                usersService.enableUser(id)
        );
    }


}