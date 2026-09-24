package com.altis.library.books.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.altis.library.books.models.dtos.BooksRequestDTO;
import com.altis.library.books.models.dtos.BooksResponseDTO;
import com.altis.library.books.models.dtos.UpdateRequestDTO;
import com.altis.library.books.models.entities.books;
import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.publishers.models.entities.publishers;
import com.altis.library.publishers.repositories.PublishersRepository;
import com.altis.library.rents.repositories.RentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class BooksServices {

    private final BooksRepository booksRepository;
    private final PublishersRepository publishersRepository;
    private final RentsRepository rentsRepository;

    public BooksServices(
            BooksRepository booksRepository,
            PublishersRepository publishersRepository,
            RentsRepository rentsRepository
    ) {
        this.booksRepository = booksRepository;
        this.publishersRepository = publishersRepository;
        this.rentsRepository = rentsRepository;
    }

    @Transactional
    public BooksResponseDTO create(BooksRequestDTO bookData) {

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
        book.setPublicationDate(LocalDate.now());
        book.setGenre(bookData.genre());
        book.setNumberPages(bookData.numberPages());
        book.setQuantity(bookData.quantity());
        book.setPublisher(publisher);

        books savedBook = booksRepository.save(book);

        return new BooksResponseDTO(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getPublicationDate(),
                savedBook.getGenre(),
                savedBook.getNumberPages(),
                savedBook.getQuantity(),
                savedBook.getPublisher().getId(),
                savedBook.getCreateDt(),
                savedBook.getUpdateDt()
        );
    }

    @Transactional
    public Page<books> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public books findById(Long id) {

        return booksRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found")
                );
    }

    @Transactional
    public books update(Long id, BooksRequestDTO bookData) {

        books book = findById(id);

        publishers publisher = publishersRepository
                .findById(bookData.publisherId())
                .orElseThrow(() ->
                        new RuntimeException("Publisher not found")
                );

        book.setTitle(bookData.title());
        book.setGenre(bookData.genre());
        book.setNumberPages(bookData.numberPages());
        book.setQuantity(bookData.quantity());
        book.setPublisher(publisher);

        return booksRepository.save(book);
    }

    @Transactional
    public books partialUpdate(
            Long id,
            UpdateRequestDTO bookData
    ) {

        books book = findById(id);

        if (bookData.title() != null) {
            book.setTitle(bookData.title());
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

    @Transactional
    public void delete(Long id) {

        books book = findById(id);

        if (rentsRepository.existsByBookId(id)) {
            throw new RuntimeException(
                    "Book cannot be deleted because it has registered rents"
            );
        }

        booksRepository.delete(book);
    }
}