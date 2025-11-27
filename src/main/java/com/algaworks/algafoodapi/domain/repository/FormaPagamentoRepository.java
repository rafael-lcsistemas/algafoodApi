package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    List<FormaPagamento> findByNomeContaining(String nome);
}
