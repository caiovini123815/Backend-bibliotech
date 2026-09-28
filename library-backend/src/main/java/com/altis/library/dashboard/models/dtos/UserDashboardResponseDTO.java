package com.altis.library.dashboard.models.dtos;

public record UserDashboardResponseDTO(
        Long userId,
        String nameFull,
        Long totalRents,
        Long activeRents,
        Long lateRents,
        Long returnedRents
) {}
