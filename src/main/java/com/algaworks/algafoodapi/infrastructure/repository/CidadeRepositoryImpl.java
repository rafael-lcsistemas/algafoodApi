package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.entity.Cidade;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Cidade> findAll() {
        return manager.createQuery("from Cidade ", Cidade.class).getResultList();
    }

    @Override
    public Cidade findById(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade insert(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public Cidade update(Cidade cidade) {
        return manager.merge(cidade);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Cidade cidade = findById(id);

        if (cidade == null) {
            throw new EmptyResultDataAccessException(1);
        }

        manager.remove(cidade);
    }
}
