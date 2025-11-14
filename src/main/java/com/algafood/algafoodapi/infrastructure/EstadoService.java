package com.algafood.algafoodapi.infrastructure;

import com.algafood.algafoodapi.domain.entity.Estado;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoService implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> findAll() {
        return manager.createQuery("from Estado ", Estado.class).getResultList();
    }

    @Override
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado insertOrUpdate(Estado estado) {
        return manager.merge(estado);
    }
}
