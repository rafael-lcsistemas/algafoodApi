package com.algafood.algafoodapi.infrastructure;

import com.algafood.algafoodapi.domain.entity.Permissao;
import com.algafood.algafoodapi.domain.repository.EstadoRepository;
import com.algafood.algafoodapi.domain.repository.PermissaoRepository;
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
