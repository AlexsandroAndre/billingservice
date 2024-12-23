package com.alexsandro.billingservice.controller;

import com.alexsandro.billingservice.dto.CargaDTO;
import com.alexsandro.billingservice.model.Carga;
import com.alexsandro.billingservice.service.CargaService;
import com.alexsandro.billingservice.service.FaturamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/cargas")
public class CargaController {

    @Autowired
    private FaturamentoService faturamentoService;

    @Autowired
    private CargaService cargaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carga criada com sucesso", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carga.class))
            }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @PostMapping
    public ResponseEntity<Carga> cadastrarCarga(@RequestBody @Valid CargaDTO cargaDTO) {
        Carga carga = Carga.builder()
                .tipo(cargaDTO.getTipo())
                .peso(cargaDTO.getPeso())
                .dataEntrada(cargaDTO.getDataEntrada())
                .dataSaida(cargaDTO.getDataSaida())
                .build();
        Carga entitiy = cargaService.criarCarga(carga);

        return ResponseEntity.created(
                URI.create(String.format("/%s/faturamento", entitiy.getId()))
        ).body(entitiy);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Carga.class))
            }),
            @ApiResponse(responseCode = "404", description = "Carga Not Found"),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    })
    @GetMapping("/{id}/faturamento")
    @Operation(summary = "Obter faturamento", description = "Este endpoint retorna o faturamento de uma carga específica.")
    public ResponseEntity<BigDecimal> calcularFaturamento(@PathVariable UUID id) {
        Carga carga = cargaService.findById(id);
        if(carga == null){
            return ResponseEntity.notFound().build();
        }
        BigDecimal valor = faturamentoService.calcularFaturamento(carga);
        return ResponseEntity.ok(valor);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "200")
    })
    @GetMapping("/faturamento")
    @Operation(summary = "Obter faturamento", description = "Este endpoint retorna o faturamento de uma carga específica.")
    public ResponseEntity<String> getAllFaturamento() {
        return ResponseEntity.ok("ok");
    }
}
