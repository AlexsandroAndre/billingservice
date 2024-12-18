package com.alexsandro.billingservice.service;

import com.alexsandro.billingservice.model.Carga;
import com.alexsandro.billingservice.repository.CargaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CargaService {

    private static final String CARGA_NAO_ENCONTRADA = "Carga não encontrada";

    private CargaRepository cargaRepository;

    public Carga findById(UUID id){
        return  cargaRepository.findById(id.toString())
                .orElseThrow(() -> new RuntimeException(CARGA_NAO_ENCONTRADA));
    }

    public Carga criarCarga(Carga carga){
        return cargaRepository.save(carga);
    }
}
