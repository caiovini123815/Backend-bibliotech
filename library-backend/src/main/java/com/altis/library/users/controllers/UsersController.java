package com.altis.library.users.controllers;

import com.altis.library.users.models.entities.users;
import com.altis.library.users.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<users> create(@RequestBody users user) {
        users createdUser = usersService.create(user);

        return ResponseEntity.ok(createdUser);
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
        return ResponseEntity.ok(usersService.update(id, userData));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        usersService.delete(id);

        return ResponseEntity.noContent().build();
    }
}