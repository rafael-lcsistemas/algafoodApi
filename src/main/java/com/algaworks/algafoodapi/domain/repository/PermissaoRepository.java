package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Permissao;

import java.util.List;

public interface PermissaoRepository {
    List<Permissao> findAll();
    Permissao findById(Long id);
    Permissao insertOrUpdate(Permissao permissao);
}
