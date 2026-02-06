package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.entity.ProdutoFoto;
import com.algaworks.algafoodapi.domain.repository.queries.ProdutoRepositoryQueries;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public ProdutoFoto save(ProdutoFoto foto) {
        return manager.merge(foto);
    }

    @Override
    public void delete(ProdutoFoto foto) {
        manager.remove(foto);
    }
}

