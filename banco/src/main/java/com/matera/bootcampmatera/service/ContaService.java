package com.matera.bootcampmatera.service;

import com.matera.bootcampmatera.dto.RequestPixDTO;
import com.matera.bootcampmatera.dto.ResponsePixDTO;
import com.matera.bootcampmatera.repository.ContaRepository;
import com.matera.bootcampmatera.exception.ContaInvalidaException;
import com.matera.bootcampmatera.model.Conta;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContaService {

    private final ContaRepository contaRepository;

    public ResponsePixDTO pix(RequestPixDTO pixDTO) {
        Conta contaOrigem = contaRepository.findByTitularCpf(pixDTO.getChaveOrigem());
        Conta contaDestino = contaRepository.findByTitularCpf(pixDTO.getChaveDestino());
        contaOrigem.enviarPix(contaDestino, pixDTO.getValor());
        contaRepository.saveAll(List.of(contaOrigem, contaDestino));
        return new ResponsePixDTO(contaOrigem.getSaldo(), contaDestino.getSaldo());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Conta criarOuAtualizar(Conta conta) throws ContaInvalidaException {
        log.info("Nome da thread: {}", Thread.currentThread().getName());

        if (isNull(conta.getAgencia())) {
            throw new ContaInvalidaException(String.format("A conta não possui agencia"));
        }
        return contaRepository.save(conta);
    }

    public Conta debitaConta(Long idConta, BigDecimal valor) throws ContaInvalidaException {
        Optional<Conta> conta = buscarPorId(idConta);
        if (conta.isPresent()) {
            conta.get().debito(valor);
            return contaRepository.save(conta.get());
        }
        throw new ContaInvalidaException("Conta inexistente");
    }

    public Conta creditarConta(Long idConta, BigDecimal valor) throws ContaInvalidaException {
        Optional<Conta> conta = buscarPorId(idConta);
        if (conta.isPresent()) {
            conta.get().credito(valor);
            return contaRepository.save(conta.get());
        }
        throw new ContaInvalidaException("Conta inexistente");
    }

    public List<Conta> getContas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> buscarPorId(Long id) {
        return contaRepository.findById(id);
    }

    public void delete(Conta conta) {
        contaRepository.delete(conta);
    }
}
