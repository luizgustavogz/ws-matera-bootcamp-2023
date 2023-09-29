package com.matera.bootcampmatera.model;

import com.matera.bootcampmatera.exception.ContaInvalidaException;
import com.matera.bootcampmatera.exception.ContaSemSaldoException;
import jakarta.persistence.*;
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
    private BigDecimal saldo = BigDecimal.ZERO;
    @OneToOne
    private Titular titular;

    public void debito(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void credito(BigDecimal valor) {
        saldo = saldo.add(valor);
    }

    public void enviarPix(Conta contaDestino, BigDecimal valor) {
        if (this.saldo.compareTo(valor) <= 0) {
            throw new ContaSemSaldoException("Conta sem saldo disponível.");
        }

        this.debito(valor);
        contaDestino.credito(valor);
    }
}

/**
 *                          @Component
 * @Controller              @Service            @Repository
 *
 * @RestController
 *
 */
