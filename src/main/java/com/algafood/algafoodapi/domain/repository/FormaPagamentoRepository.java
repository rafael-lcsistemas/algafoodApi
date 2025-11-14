package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.entity.FormaPagamento;

import java.util.List;

public interface FormaPagamentoRepository {
    List<FormaPagamento> findAll();
    FormaPagamento findById(Long id);
    FormaPagamento insertOrUpdate(FormaPagamento formaPagamento);
}
