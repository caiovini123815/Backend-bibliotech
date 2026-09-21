package com.altis.library.books.services;

import com.altis.library.books.models.dtos.BooksRequestDTO;
import com.altis.library.books.models.dtos.UpdateRequestDTO;
import com.altis.library.books.models.entities.books;
import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.publishers.models.entities.publishers;
import com.altis.library.publishers.repositories.PublishersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServices {

    private final BooksRepository booksRepository;
    private final PublishersRepository publishersRepository;

    public BooksServices(
            BooksRepository booksRepository,
            PublishersRepository publishersRepository
    ) {
        this.booksRepository = booksRepository;
        this.publishersRepository = publishersRepository;
    }

    public books create(BooksRequestDTO bookData) {

        if (booksRepository.existsByIsbn(bookData.isbn())) {
            throw new RuntimeException("ISBN already registered");
        }

        publishers publisher = publishersRepository
                .findById(bookData.publisherId())
                .orElseThrow(() ->
                        new RuntimeException("Publisher not found")
                );

        books book = new books();

        book.setTitle(bookData.title());
        book.setIsbn(bookData.isbn());
        book.setPublicationDate(bookData.publicationDate());
        book.setGenre(bookData.genre());
        book.setNumberPages(bookData.numberPages());
        book.setQuantity(bookData.quantity());

        book.setPublisher(publisher);

        return booksRepository.save(book);
    }

    public List<books> findAll() {
        return booksRepository.findAll();
    }

    public books findById(Long id) {

        return booksRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found")
                );
    }

    public books update(Long id, BooksRequestDTO bookData) {

        books book = findById(id);

        publishers publisher = publishersRepository
                .findById(bookData.publisherId())
                .orElseThrow(() ->
                        new RuntimeException("Publisher not found")
                );

        book.setTitle(bookData.title());
        book.setIsbn(bookData.isbn());
        book.setPublicationDate(bookData.publicationDate());
        book.setGenre(bookData.genre());
        book.setNumberPages(bookData.numberPages());
        book.setQuantity(bookData.quantity());
        book.setPublisher(publisher);

        return booksRepository.save(book);
    }

    public books partialUpdate(
            Long id,
            UpdateRequestDTO bookData
    ) {

        books book = findById(id);

        if (bookData.title() != null) {
            book.setTitle(bookData.title());
        }

        if (bookData.publicationDate() != null) {
            book.setPublicationDate(bookData.publicationDate());
        }

        if (bookData.genre() != null) {
            book.setGenre(bookData.genre());
        }

        if (bookData.numberPages() != null) {
            book.setNumberPages(bookData.numberPages());
        }

        if (bookData.quantity() != null) {
            book.setQuantity(bookData.quantity());
        }

        if (bookData.publisherId() != null) {

            publishers publisher = publishersRepository
                    .findById(bookData.publisherId())
                    .orElseThrow(() ->
                            new RuntimeException("Publisher not found")
                    );

            book.setPublisher(publisher);
        }

        return booksRepository.save(book);
    }

    public void delete(Long id) {

        books book = findById(id);

        booksRepository.delete(book);
    }
}