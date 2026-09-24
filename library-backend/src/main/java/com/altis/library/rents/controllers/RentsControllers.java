package com.altis.library.rents.controllers;

import com.altis.library.rents.models.dtos.RentsRequestDTO;
import com.altis.library.rents.models.dtos.RentsResponseDTO;
import com.altis.library.rents.models.dtos.UpdateRequestDTO;
import com.altis.library.rents.models.entities.rents;
import com.altis.library.rents.services.RentsServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rents")
public class RentsControllers {

    private final RentsServices rentsServices;

    public RentsControllers(RentsServices rentsServices) {
        this.rentsServices = rentsServices;
    }

    @PostMapping
    public ResponseEntity<RentsResponseDTO> create(
            @Valid @RequestBody RentsRequestDTO rentData) {

        RentsResponseDTO createdRent =
                rentsServices.create(rentData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdRent.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(createdRent);
    }

    @GetMapping
    public ResponseEntity<List<rents>> findAll() {
        return ResponseEntity.ok(rentsServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<rents> findById(@PathVariable Long id) {
        return ResponseEntity.ok(rentsServices.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<rents> update(
            @PathVariable Long id,
            @RequestBody @Valid RentsRequestDTO rentData
    ) {
        return ResponseEntity.ok(
                rentsServices.update(id, rentData)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<rents> partialUpdate(
            @PathVariable Long id,
            @RequestBody @Valid UpdateRequestDTO rentData
    ) {
        return ResponseEntity.ok(
                rentsServices.partialUpdate(id, rentData)
        );
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<RentsResponseDTO> returnPeriod(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                rentsServices.returnBook(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        rentsServices.delete(id);

        return ResponseEntity.noContent().build();
    }
}