package com.algafood.algafoodapi.infrastructure;

import com.algafood.algafoodapi.domain.entity.FormaPagamento;
import com.algafood.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class FormaPagamentoService implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<FormaPagamento> findAll() {
        return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
    }

    @Override
    public FormaPagamento findById(Long id) {
        return manager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public FormaPagamento insertOrUpdate(FormaPagamento formaPagamento) {
        return manager.merge(formaPagamento);
    }
}
