package com.algaworks.algafoodapi.infrastructure;

import com.algaworks.algafoodapi.domain.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class PermissaoService implements PermissaoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Permissao> findAll() {
        return manager.createQuery("from Permissao ", Permissao.class).getResultList();
    }

    @Override
    public Permissao findById(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public Permissao insertOrUpdate(Permissao permissao) {
        return manager.merge(permissao);
    }
}
