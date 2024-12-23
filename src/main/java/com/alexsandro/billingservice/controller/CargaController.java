package com.alexsandro.billingservice.controller;

import com.alexsandro.billingservice.model.Carga;
import com.alexsandro.billingservice.service.CargaService;
import com.alexsandro.billingservice.service.FaturamentoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/cargas")
public class CargaController {

    @Autowired
    private FaturamentoService faturamentoService;

    @Autowired
    private CargaService cargaService;

    @PostMapping
    public ResponseEntity<Carga> cadastrarCarga(@RequestBody Carga carga) {
        Carga entitiy = cargaService.criarCarga(carga);
        return ResponseEntity.ok(entitiy);
    }

    @GetMapping("/{id}/faturamento")
    @Operation(summary = "Obter faturamento", description = "Este endpoint retorna o faturamento de uma carga específica.")
    public ResponseEntity<BigDecimal> calcularFaturamento(@PathVariable UUID id) {
        Carga carga = cargaService.findById(id);
        BigDecimal valor = faturamentoService.calcularFaturamento(carga);
        return ResponseEntity.ok(valor);
    }
}
