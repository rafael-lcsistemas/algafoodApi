package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.entity.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> findAll();
    Cozinha findById(Long id);
    Cozinha insertOrUpdate(Cozinha cozinha);
    void delete(Cozinha cozinha);
}
