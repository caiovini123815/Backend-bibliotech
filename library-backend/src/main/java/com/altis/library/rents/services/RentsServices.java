package com.altis.library.rents.services;

import com.altis.library.books.models.entities.books;
import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.rents.models.dtos.RentsRequestDTO;
import com.altis.library.rents.models.dtos.UpdateRequestDTO;
import com.altis.library.rents.models.entities.rents;
import com.altis.library.rents.repositories.RentsRepository;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public rents create(RentsRequestDTO rentData) {

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

        rents rent = new rents();

        rent.setUser(user);
        rent.setBook(book);
        rent.setRentDate(rentData.rentDate());
        rent.setReturnPeriod(rentData.returnPeriod());
        rent.setReturnDate(rentData.returnDate());
        rent.setStatus(rentData.status());

        return rentsRepository.save(rent);
    }

    public List<rents> findAll() {
        return rentsRepository.findAll();
    }

    public rents findById(Long id) {

        return rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );
    }

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
        rent.setRentDate(rentData.rentDate());
        rent.setReturnPeriod(rentData.returnPeriod());
        rent.setReturnDate(rentData.returnDate());
        rent.setStatus(rentData.status());

        return rentsRepository.save(rent);
    }

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

        if (rentData.rentDate() != null) {
            rent.setRentDate(rentData.rentDate());
        }

        if (rentData.returnPeriod() != null) {
            rent.setReturnPeriod(rentData.returnPeriod());
        }

        if (rentData.returnDate() != null) {
            rent.setReturnDate(rentData.returnDate());
        }

        if (rentData.status() != null) {
            rent.setStatus(rentData.status());
        }

        return rentsRepository.save(rent);
    }

    public void delete(Long id) {

        rents rent = rentsRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Rent not found")
                );

        rentsRepository.delete(rent);
    }
}