package com.matera.bootcampmatera.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestPixDTO {

    private String chaveOrigem;
    private String chaveDestino;
    private BigDecimal valor;
}
