package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.entity.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> findAll();
    Restaurante findById(Long id);
    Restaurante insertOrUpdate(Restaurante restaurante);
    void delete(Restaurante restaurante);
}
