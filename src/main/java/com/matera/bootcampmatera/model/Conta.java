package com.matera.bootcampmatera.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity // CONTA
@Table(name = "Conta")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero")
    private String numConta; //NUMERO_CONTA
    private String agencia; // AGENCIA

    public Conta(){}

    public Conta(Long id, String numConta, String agencia) {
        this.id = id;
        this.numConta = numConta;
        this.agencia = agencia;
    }
}

/**
 *                          @Component
 * @Controller              @Service            @Repository
 *
 * @RestController
 *
 */
