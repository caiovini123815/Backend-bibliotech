package com.altis.library.dashboard.services;

import com.altis.library.books.repositories.BooksRepository;
import com.altis.library.dashboard.models.dtos.AdminDashboardResponseDTO;
import com.altis.library.dashboard.models.dtos.UserDashboardResponseDTO;
import com.altis.library.publishers.repositories.PublishersRepository;
import com.altis.library.rents.repositories.RentsRepository;
import com.altis.library.users.models.entities.users;
import com.altis.library.users.repositories.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardServices {

    private final UsersRepository usersRepository;
    private final BooksRepository booksRepository;
    private final PublishersRepository publishersRepository;
    private final RentsRepository rentsRepository;

    public DashboardServices(
            UsersRepository usersRepository,
            BooksRepository booksRepository,
            PublishersRepository publishersRepository,
            RentsRepository rentsRepository
    ) {
        this.usersRepository = usersRepository;
        this.booksRepository = booksRepository;
        this.publishersRepository = publishersRepository;
        this.rentsRepository = rentsRepository;
    }

    @Transactional(readOnly = true)
    public AdminDashboardResponseDTO adminDashboard() {

        long totalUsers = usersRepository.count();
        long totalBooks = booksRepository.count();
        long totalPublishers = publishersRepository.count();
        long totalRents = rentsRepository.count();

        long activeRents =
                rentsRepository.countByStatus("RENTED");

        long lateRents =
                rentsRepository.countByStatus("LATE");

        return new AdminDashboardResponseDTO(
                totalUsers,
                totalBooks,
                totalPublishers,
                totalRents,
                activeRents,
                lateRents
        );
    }

    @Transactional(readOnly = true)
    public UserDashboardResponseDTO userDashboard(Long userId) {

        users user = usersRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        long totalRents =
                rentsRepository.countByUserId(userId);

        long activeRents =
                rentsRepository.countByUserIdAndStatus(
                        userId,
                        "RENTED"
                );

        long lateRents =
                rentsRepository.countByUserIdAndStatus(
                        userId,
                        "LATE"
                );

        long returnedRents =
                rentsRepository.countByUserIdAndStatus(
                        userId,
                        "RETURNED"
                );

        return new UserDashboardResponseDTO(
                user.getId(),
                user.getNameFull(),
                totalRents,
                activeRents,
                lateRents,
                returnedRents
        );
    }
}