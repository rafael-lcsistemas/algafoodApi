package com.algafood.algafoodapi.domain.service;

import com.algafood.algafoodapi.domain.entity.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CozinhaService {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> findAll() {
      return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    public Cozinha findById(Long id) {
        return manager.find(Cozinha.class, id);
    }

    @Transactional
    public Cozinha insertOrUpdate(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    @Transactional
    public void delete(Cozinha cozinha) {
        cozinha = findById(cozinha.getId());
        manager.remove(cozinha);
    }
}
