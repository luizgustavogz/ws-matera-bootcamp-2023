package com.matera.bootcampmatera.model;

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

    @Column(name = "NUMERO")
    private String numConta; //NUMERO_CONTA
    @Column(name = "AGENCIA")
    private String agencia; // AGENCIA
    @Column(name = "SALDO")
    private BigDecimal saldo = BigDecimal.ZERO;
    @OneToOne
    @Column(name = "TITULAR")
    private Titular titular;

    public void debito(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    public void credito(BigDecimal valor) {
        saldo = saldo.add(valor);
    }
}

/**
 *                          @Component
 * @Controller              @Service            @Repository
 *
 * @RestController
 *
 */
