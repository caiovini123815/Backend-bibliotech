package com.altis.library.users.repositories;

import com.altis.library.users.models.entities.users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<users, Long> {

    Optional<users> findByEmail(String email);

    Optional<users> findByCpf(String cpf);

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

}
