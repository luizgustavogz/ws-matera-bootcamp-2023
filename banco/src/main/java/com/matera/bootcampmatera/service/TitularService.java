package com.matera.bootcampmatera.service;

import com.matera.bootcampmatera.model.Titular;
import com.matera.bootcampmatera.repository.TitularRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TitularService {

    private final TitularRepository titularRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Titular criarOuAtualizar(Titular titular) {
        log.info("Criando ou atualizando o titular do nome: {}", titular.getNome());
        log.info("Nome da thread: {}", TransactionSynchronizationManager.getCurrentTransactionName());

        return titularRepository.save(titular);
    }
}
