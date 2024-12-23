package com.alexsandro.billingservice.repository;

import com.alexsandro.billingservice.model.Carga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargaRepository extends JpaRepository<Carga, String> {
}
