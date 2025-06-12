package com.proposta.app.repository;

import com.proposta.app.entity.Proposta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    //query derivada
    List<Proposta> findAllByIntegradaFalse();
}
