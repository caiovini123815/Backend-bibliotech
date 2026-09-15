package com.altis.library.publishers.repositories;

import com.altis.library.publishers.models.entities.publishers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublishersRepository  extends JpaRepository<publishers, Long> {

    boolean existsByEmail(String email);
    boolean existsByCnpj(String cnpj);

}
