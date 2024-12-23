package com.alexsandro.billingservice.repository;

import com.alexsandro.billingservice.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CargaRepository extends JpaRepository<Carga, String> {

    Optional<Carga> findById(UUID id);
}
