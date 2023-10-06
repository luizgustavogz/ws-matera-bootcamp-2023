package com.matera.bootcampmatera.repository;

import com.matera.bootcampmatera.model.Titular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TitularRepository extends JpaRepository<Titular, Long> {

    Optional<Titular> findByCpf(String cpf);
}
