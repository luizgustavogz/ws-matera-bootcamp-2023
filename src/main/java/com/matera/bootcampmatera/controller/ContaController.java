package com.matera.bootcampmatera.controller;

import com.matera.bootcampmatera.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<Conta> getAll() {
        return contaService.getContas();
    }

    @PostMapping
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.criar(conta));
    }

    // http://localhost:8080/contas/1
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        List<Conta> contas = contaService.getContas();
        Optional<Conta> contaOptional = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody Conta contaAtualizada) {
        List<Conta> contas = contaService.getContas();
        Optional<Conta> contaOptional = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            contas.remove(conta);
            contaAtualizada.setId(conta.getId());
            contas.add(contaAtualizada);
            return ResponseEntity.ok(contaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        List<Conta> contas = contaService.getContas();
        Optional<Conta> contaOptional = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            contas.remove(conta);
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
