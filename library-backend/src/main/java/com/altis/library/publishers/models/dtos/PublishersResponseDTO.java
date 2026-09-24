package com.altis.library.publishers.models.dtos;

import java.time.LocalDateTime;

public record PublishersResponseDTO(
        Long id,
        String namePublisher,
        String phonePublisher,
        String website,
        LocalDateTime createDt,
        LocalDateTime updateDt


) {}
