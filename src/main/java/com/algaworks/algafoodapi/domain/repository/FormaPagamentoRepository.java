package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> findAll();
    FormaPagamento findById(Long id);
    FormaPagamento insert(FormaPagamento formaPagamento);
    FormaPagamento update(FormaPagamento formaPagamento);
    void delete(Long id);
}
