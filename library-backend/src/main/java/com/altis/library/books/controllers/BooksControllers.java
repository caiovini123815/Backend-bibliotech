package com.altis.library.books.controllers;

import com.altis.library.books.models.dtos.BooksResponseDTO;
import com.altis.library.books.services.BooksServices;
import com.altis.library.books.models.dtos.BooksRequestDTO;
import com.altis.library.books.models.dtos.UpdateRequestDTO;
import com.altis.library.books.models.entities.books;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/books")
public class BooksControllers {

    private final BooksServices booksServices;

    public BooksControllers(BooksServices booksServices) {
        this.booksServices = booksServices;
    }

    @PostMapping
    public ResponseEntity<BooksResponseDTO> create(
            @Valid @RequestBody BooksRequestDTO bookData) {

        BooksResponseDTO createdBook =
                booksServices.create(bookData);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBook.id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(createdBook);
    }

    @GetMapping
    public Page<books> findAll(Pageable pageable) {
        return booksServices.findAll(pageable);
    }

    @GetMapping("/{id}")
    public books findById(@PathVariable Long id) {
        return booksServices.findById(id);
    }

    @PutMapping("/{id}")
    public books update(
            @PathVariable Long id,
            @RequestBody BooksRequestDTO bookData
    ) {
        return booksServices.update(id, bookData);
    }

    @PatchMapping("/{id}")
    public books partialUpdate(
            @PathVariable Long id,
            @RequestBody UpdateRequestDTO bookData
    ) {
        return booksServices.partialUpdate(id, bookData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        booksServices.delete(id);

        return ResponseEntity.noContent().build();
    }
}