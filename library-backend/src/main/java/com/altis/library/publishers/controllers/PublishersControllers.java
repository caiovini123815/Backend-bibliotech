package com.altis.library.publishers.controllers;

import com.altis.library.publishers.models.dtos.PublishersRequestDTO;
import com.altis.library.publishers.models.dtos.PublishersResponseDTO;
import com.altis.library.publishers.models.entities.publishers;
import com.altis.library.publishers.services.PublishersServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublishersControllers {

    private final PublishersServices publishersServices;

    public PublishersControllers(PublishersServices publishersServices) {
        this.publishersServices = publishersServices;
    }

    @PostMapping
    public ResponseEntity<PublishersResponseDTO> create(
            @Valid @RequestBody PublishersRequestDTO publisherData) {

        PublishersResponseDTO createdPublisher =
                publishersServices.create(publisherData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPublisher.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(createdPublisher);
    }

    @GetMapping
    public ResponseEntity<List<publishers>> findAll() {

        return ResponseEntity.ok(publishersServices.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<publishers> findById(@PathVariable Long id) {

        return ResponseEntity.ok(publishersServices.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<publishers> update(
            @PathVariable Long id,
            @RequestBody publishers publisherData) {

        return ResponseEntity.ok(
                publishersServices.update(id, publisherData)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<publishers> partialUpdate(
            @PathVariable Long id,
            @RequestBody publishers publisherData) {

        return ResponseEntity.ok(
                publishersServices.partialUpdate(id, publisherData)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        publishersServices.delete(id);

        return ResponseEntity.noContent().build();
    }
}