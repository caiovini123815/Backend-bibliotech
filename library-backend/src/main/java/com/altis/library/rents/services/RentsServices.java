package com.altis.library.rents.services;

import com.altis.library.books.models.entities.books;
import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.rents.models.dtos.RentsRequestDTO;
import com.altis.library.rents.models.dtos.RentsResponseDTO;
import com.altis.library.rents.models.dtos.UpdateRequestDTO;
import com.altis.library.rents.models.entities.rents;
import com.altis.library.rents.repositories.RentsRepository;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
public class RentsServices {

    private final RentsRepository rentsRepository;
    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;

    public RentsServices(
            RentsRepository rentsRepository,
            UsersRepository usersRepository,
            BooksRepository booksRepository
    ) {
        this.rentsRepository = rentsRepository;
        this.usersRepository = usersRepository;
        this.booksRepository = booksRepository;
    }

    @Transactional
    public RentsResponseDTO create(RentsRequestDTO rentData) {

        users user = usersRepository
                .findById(rentData.userId())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        books book = booksRepository
                .findById(rentData.bookId())
                .orElseThrow(() ->
                        new RuntimeException("Book not found")
                );

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book out of stock");
        }

        LocalDate rentDate = LocalDate.now();
        rents rent = new rents();
        rent.setUser(user);
        rent.setBook(book);
        rent.setRentDate(rentDate);
        rent.setReturnPeriod(null);
        rent.setReturnDate(rentDate.plusDays(14));
        rent.setStatus("RENTED");

        book.setQuantity(book.getQuantity() - 1);

        booksRepository.save(book);

        rents savedRent = rentsRepository.save(rent);

        return new RentsResponseDTO(
                savedRent.getId(),
                savedRent.getUser(),
                savedRent.getBook(),
                savedRent.getRentDate(),
                savedRent.getReturnPeriod(),
                savedRent.getReturnDate(),
                savedRent.getStatus(),
                savedRent.getCreateDt(),
                savedRent.getUpdateDt()
        );
    }

    @Transactional(readOnly = true)
    public Page<rents> findAll(Pageable pageable) {
        return rentsRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public rents findById(Long id) {

        return rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found"));
    }

    @Transactional
    public rents update(Long id, RentsRequestDTO rentData) {

        rents rent = rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );

        users user = usersRepository
                .findById(rentData.userId())
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        books book = booksRepository
                .findById(rentData.bookId())
                .orElseThrow(() ->
                        new RuntimeException("Book not found")
                );

        rent.setUser(user);
        rent.setBook(book);

        return rentsRepository.save(rent);
    }

    @Transactional
    public rents partialUpdate(Long id, UpdateRequestDTO rentData) {

        rents rent = rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );

        if (rentData.userId() != null) {

            users user = usersRepository
                    .findById(rentData.userId())
                    .orElseThrow(() ->
                            new RuntimeException("User not found")
                    );

            rent.setUser(user);
        }

        if (rentData.bookId() != null) {

            books book = booksRepository
                    .findById(rentData.bookId())
                    .orElseThrow(() ->
                            new RuntimeException("Book not found")
                    );

            rent.setBook(book);
        }



        return rentsRepository.save(rent);
    }

    @Transactional
    public RentsResponseDTO returnBook(Long id) {

        rents rent = rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );

        if ("RETURNED".equals(rent.getStatus())) {
            throw new RuntimeException("Book already returned");
        }

        books book = rent.getBook();
        rent.setReturnPeriod(LocalDate.now());
        rent.setStatus("RETURNED");
        book.setQuantity(book.getQuantity() + 1);
        booksRepository.save(book);
        rents savedRent = rentsRepository.save(rent);

        return new RentsResponseDTO(
                savedRent.getId(),
                savedRent.getUser(),
                savedRent.getBook(),
                savedRent.getRentDate(),
                savedRent.getReturnPeriod(),
                savedRent.getReturnDate(),
                savedRent.getStatus(),
                savedRent.getCreateDt(),
                savedRent.getUpdateDt()
        );
    }


    @Transactional(readOnly = true)
    public Page<rents> findMyRents(
            Long userId,
            Pageable pageable
    ) {

        return rentsRepository.findByUserId(
                userId,
                pageable
        );
    }

    @Transactional
    public void delete(Long id) {

        rents rent = rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );

        if (!"RETURNED".equals(rent.getStatus())) {
            throw new RuntimeException(
                    "Rent cannot be deleted because the book has not been returned"
            );
        }

        rentsRepository.delete(rent);
    }
}