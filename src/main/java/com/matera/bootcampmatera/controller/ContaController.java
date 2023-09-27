package com.matera.bootcampmatera.controller;

import com.matera.bootcampmatera.client.BacenClient;
import com.matera.bootcampmatera.dto.ContaDTO;
import com.matera.bootcampmatera.dto.RequestPixDTO;
import com.matera.bootcampmatera.dto.ResponsePixDTO;
import com.matera.bootcampmatera.exception.ContaInvalidaException;
import com.matera.bootcampmatera.model.Conta;
import com.matera.bootcampmatera.model.Titular;
import com.matera.bootcampmatera.service.ContaService;
import com.matera.bootcampmatera.service.TitularService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {
//    Primeira maneira
//    @Autowired
//    private ContaService contaService;

//    Segunda maneira: adicionando no construtor ou usando @RequiredArgsConstructor do lombok
//    private final ContaService contaService;
//    public ContaController(ContaService contaService) {
//        this.contaService = contaService;
//    }

    private final ContaService contaService;
    private final TitularService titularService;
    private final BacenClient bacenClient;

    @GetMapping
    public List<Conta> getAll() {
        return contaService.getContas();
    }

    @PostMapping("/lancamentos/pix")
    public ResponseEntity<ResponsePixDTO> pix(@RequestBody RequestPixDTO pixDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(contaService.pix(pixDTO));
    }

    @PostMapping("/lancamentos/{idConta}/debito/{valor}")
    public ResponseEntity<Conta> debitar(@PathVariable Long idConta, @PathVariable BigDecimal valor) throws ContaInvalidaException {
        Conta conta = contaService.debitaConta(idConta, valor);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/lancamentos/{idConta}/credito/{valor}")
    public ResponseEntity<Conta> creditar(@PathVariable Long idConta, @PathVariable BigDecimal valor) throws ContaInvalidaException {
        Conta conta = contaService.creditarConta(idConta, valor);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta) throws ContaInvalidaException {
        Titular titular = conta.getTitular();
        Titular titularSalvo = titularService.criarOuAtualizar(titular); //TODO: dualwrite?

//        ContaDTO contaDTO = ContaDTO.builder()
//                .numConta(conta.getNumConta())
//                .saldo(conta.getSaldo())
//                .agencia(conta.getAgencia())
//                .chavePix(titularSalvo.getCpf())
//                .build();

        ContaDTO contaDTO = new ContaDTO(conta.getNumConta(), conta.getAgencia(), titularSalvo.getCpf());
        bacenClient.criarConta(contaDTO);

        conta.setTitular(titular);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criarOuAtualizar(conta));
    }

    // http://localhost:8080/contas/1
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        Optional<Conta> contaOptional = contaService.buscarPorId(id);
        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody Conta contaAtualizada) throws ContaInvalidaException {
        Optional<Conta> contaOptional = contaService.buscarPorId(id);

        if (contaOptional.isPresent()) {
            contaAtualizada.setId(id);
            contaService.criarOuAtualizar(contaAtualizada);
            return ResponseEntity.ok(contaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Conta> contaOptional = contaService.buscarPorId(id);

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            contaService.delete(conta);
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    Terceira maneira: "set injection"
//    private  ContaService contaService;
//    @Autowired
//    public void setContaService(ContaService contaService){
//        this.contaService = contaService;
//    }
}
