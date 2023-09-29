package com.matera.bootcampmatera.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
//@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ContaDTO {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numConta;
    private String agencia;
    private String chavePix;
    private BigDecimal saldo = BigDecimal.ZERO;
//    @OneToOne
//    private Titular titular;

    public ContaDTO(String numConta, String agencia, String chavePix) {
        this.numConta = numConta;
        this.agencia = agencia;
        this.chavePix = chavePix;
    }
//
//    public void debito(BigDecimal valor){
//        saldo = saldo.subtract(valor);
//    }
//
//    public void enviarPix(Conta contaDestino, BigDecimal valor) {
//
//        if (this.saldo.compareTo(valor) <= 0){
//            throw new ContaInvalidaException("Conta sem saldo disponível.");
//        }
//
//        this.debito(valor);
//        contaDestino.credito(valor);
//    }
//
//    public void credito(BigDecimal valor){
//        saldo = saldo.add(valor);
//    }

}