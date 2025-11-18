package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.entity.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> findAll();
    Cozinha findById(Long id);
    Cozinha insert(Cozinha cozinha);
    Cozinha update(Cozinha cozinha);
    void delete(Long id);
}
