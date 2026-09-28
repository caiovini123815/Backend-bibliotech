package com.altis.library.dashboard.models.dtos;

public record AdminDashboardResponseDTO(
        Long totalUsers,
        Long totalBooks,
        Long totalPublishers,
        Long totalRents,
        Long activeRents,
        Long lateRents
) {}
