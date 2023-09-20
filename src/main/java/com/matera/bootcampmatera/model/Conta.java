package com.matera.bootcampmatera.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Conta {

    private Long id;
    private String numConta;
    private String agencia;

}

/**
 *                          @Component
 * @Controller              @Service            @Repository
 *
 * @RestController
 *
 */
