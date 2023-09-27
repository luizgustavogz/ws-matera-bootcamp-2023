package com.matera.bootcampmaterabacen.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "Conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numConta;
    private String agencia;
    private String chavePix;
    private BigDecimal saldo = BigDecimal.ZERO;
//    @OneToOne
//    private Titular titular;

//    public void debito(BigDecimal valor) {
//        saldo = saldo.subtract(valor);
//    }
//
//    public void credito(BigDecimal valor) {
//        saldo = saldo.add(valor);
//    }
//
//    public void enviarPix(Conta contaDestino, BigDecimal valor) {
//        if (this.saldo.compareTo(valor) <= 0) {
//            throw new ContaSemSaldoException("Conta sem saldo disponível.");
//        }
//
//        this.debito(valor);
//        contaDestino.credito(valor);
//    }
}
