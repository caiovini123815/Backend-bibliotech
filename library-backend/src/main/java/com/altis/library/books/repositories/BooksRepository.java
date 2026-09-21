package com.altis.library.books.repositories;

import com.altis.library.books.models.entities.books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<books, Long> {

    boolean existsByIsbn(String isbn);

}