package com.matera.bootcampmatera.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ResponsePixDTO {

    private UUID id;
    private BigDecimal saldoOrigem;
    private BigDecimal saldoDestino;

    public ResponsePixDTO(BigDecimal saldoOrigem, BigDecimal saldoDestino) {
        this.id = UUID.randomUUID();
        this.saldoOrigem = saldoOrigem;
        this.saldoDestino = saldoDestino;
    }
}
