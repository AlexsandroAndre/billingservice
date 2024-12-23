package com.alexsandro.billingservice.dto;

import com.alexsandro.billingservice.enuns.TipoCarga;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Representa os dados necessários para cadastrar uma carga.")
@Getter
@Setter
@Builder
public class CargaDTO {
    @NotNull(message = "O tipo da carga não pode ser nulo")
    @Schema(description = "Tipo da carga, como 'PRODUTO', 'PERIGOSA', 'FRAGIL', 'PESADA', 'EMBALADA'.", example = "Produto")
    private TipoCarga tipo;

    @NotNull(message = "O peso da carga não pode ser nulo")
    @DecimalMin(value = "0.01", inclusive = true, message = "O peso deve ser maior que zero")
    @Schema(description = "Peso total da carga em quilogramas.", example = "2500.75")
    private BigDecimal peso;

    @NotNull(message = "A data de entrada não pode ser nula")
    @Schema(description = "Data e hora de entrada da carga no sistema.", example = "2024-12-23T15:30:00")
    private LocalDateTime dataEntrada;

    @Schema(description = "Data e hora da saída da carga no sistema.", example = "2024-12-23T15:30:00")
    private LocalDateTime dataSaida;
}
