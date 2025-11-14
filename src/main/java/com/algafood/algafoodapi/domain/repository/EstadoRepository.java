package com.algafood.algafoodapi.domain.repository;

import com.algafood.algafoodapi.domain.entity.Estado;

import java.util.List;

public interface EstadoRepository {
    List<Estado> findAll();
    Estado findById(Long id);
    Estado insertOrUpdate(Estado estado);
}
