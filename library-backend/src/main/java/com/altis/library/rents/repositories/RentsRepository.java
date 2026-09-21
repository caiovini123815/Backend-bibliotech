package com.altis.library.rents.repositories;

import com.altis.library.rents.models.entities.rents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentsRepository extends JpaRepository<rents, Long> {

}