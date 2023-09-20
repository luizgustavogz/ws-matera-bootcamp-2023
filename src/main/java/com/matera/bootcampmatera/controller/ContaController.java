package com.matera.bootcampmatera.controller;

import com.matera.bootcampmatera.model.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {
    //    Priemira maneira
    //    @Autowired
    //    private ContaService contaService;

    //    Segunda maneira: adicionando no construtor ou usando @RequiredArgsConstructor do lombok
    //    private final ContaService contaService;
    //    public ContaController(ContaService contaService) {
    //        this.contaService = contaService;
    //    }

    private final ContaService contaService;

    @GetMapping
    public List<Conta> teste() {
        return contaService.getContas();
    }

    @PostMapping
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta){
        return ResponseEntity.ok(contaService.criar(conta));
    }

    // http://localhost:8080/contas/1
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id){
        List<Conta> contas = contaService.getContas();
        Optional<Conta> contaOptional = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();
        if (contaOptional.isPresent()){
            Conta conta = contaOptional.get();
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //    Terceira maneira: "setinjection"
    //    private  ContaService contaService;
    //    @Autowired
    //    public void setContaService(ContaService contaService){
    //        this.contaService = contaService;
    //    }
}
