package com.altis.library.books.controllers;

import com.altis.library.books.services.BooksServices;
import com.altis.library.books.models.dtos.BooksRequestDTO;
import com.altis.library.books.models.dtos.UpdateRequestDTO;
import com.altis.library.books.models.entities.books;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksControllers {

    private final BooksServices booksServices;

    public BooksControllers(BooksServices booksServices) {
        this.booksServices = booksServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public books create(@RequestBody BooksRequestDTO bookData) {
        return booksServices.create(bookData);
    }

    @GetMapping
    public List<books> findAll() {
        return booksServices.findAll();
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