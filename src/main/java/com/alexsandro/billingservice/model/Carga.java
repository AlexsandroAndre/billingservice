package com.alexsandro.billingservice.model;

import com.alexsandro.billingservice.enuns.TipoCarga;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "cargas")
@Builder
public class Carga {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "ID único da carga", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Column(nullable = false)
    @Schema(description = "Tipo da carga, como 'Produto', 'Perigosa', 'Frágil', etc.", example = "Produto")
    private TipoCarga tipo;

    @Column(nullable = false, precision = 10, scale = 2)
    @Schema(description = "Peso total da carga em quilogramas.", example = "2500.75")
    private BigDecimal peso;

    @Column(nullable = false)
    @Schema(description = "Data e hora de entrada da carga no sistema.", example = "2024-12-23T15:30:00")
    private LocalDateTime dataEntrada;

    @Column(nullable = true)
    @Schema(description = "Data e hora de saída da carga, se aplicável.", example = "2024-12-25T10:00:00")
    private LocalDateTime dataSaida;
}
