package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.entity.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> findAll();
    Cidade findById(Long id);
    Cidade insert(Cidade cidade);
    Cidade update(Cidade cidade);
    void delete(Long id);
}
