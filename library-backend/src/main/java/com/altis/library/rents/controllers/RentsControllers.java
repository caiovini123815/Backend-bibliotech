package com.altis.library.rents.controllers;

import com.altis.library.rents.models.dtos.RentsRequestDTO;
import com.altis.library.rents.models.dtos.RentsResponseDTO;
import com.altis.library.rents.models.dtos.UpdateRequestDTO;
import com.altis.library.rents.models.entities.rents;
import com.altis.library.rents.services.RentsServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;


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
    public ResponseEntity<Page<rents>> findAll(
            Pageable pageable
    ) {
        return ResponseEntity.ok(
                rentsServices.findAll(pageable)
        );
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

    @GetMapping("/me")
    public ResponseEntity<Page<rents>> findMyRents(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ) {

        Long userId = jwt.getClaim("userId");

        return ResponseEntity.ok(
                rentsServices.findMyRents(
                        userId,
                        pageable
                )
        );
    }

}