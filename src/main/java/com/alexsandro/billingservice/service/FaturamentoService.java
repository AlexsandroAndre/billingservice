package com.alexsandro.billingservice.service;

import com.alexsandro.billingservice.model.Carga;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class FaturamentoService {

    public BigDecimal calcularFaturamento(Carga carga) {
        BigDecimal valorBase = carga.getTipo().equals("20 pés") ? BigDecimal.valueOf(200) : BigDecimal.valueOf(300);
        BigDecimal adicionalPeso = carga.getPeso().compareTo(BigDecimal.valueOf(20)) > 0
                ? BigDecimal.valueOf(50).multiply(carga.getPeso().subtract(BigDecimal.valueOf(20)))
                : BigDecimal.ZERO;

        long dias = ChronoUnit.DAYS.between(carga.getDataEntrada(), carga.getDataSaida());
        BigDecimal armazenagem = dias <= 10 ? BigDecimal.valueOf(dias * 10)
                : dias <= 20 ? BigDecimal.valueOf(10 * 10 + (dias - 10) * 15)
                : BigDecimal.valueOf(10 * 10 + 10 * 15 + (dias - 20) * 20);

        return valorBase.add(adicionalPeso).add(armazenagem);
    }
}
