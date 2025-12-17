package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> buscarTodas() {
        try {
            return formaPagamentoRepository.findAll();
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar todas as formas de pagamento");
        }
    }

    public List<FormaPagamento> filtrarPorNome(String nome) {
        try {
            return formaPagamentoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar formas de pagamento por nome");
        }
    }

    public FormaPagamento filtrarPorID(UUID id) {
        return formaPagamentoRepository.findById(id).orElseThrow(() ->
                new FormaPagamentoNaoEncontradaException(id));
    }

    @Transactional
    public FormaPagamento inserirOuAtualizar(FormaPagamento formaPagamento) {
        if (formaPagamento.getCodInterno() == null) {
            formaPagamento.setCodInterno(getLastCodInterno() + 1);
        }

        return formaPagamentoRepository.save(formaPagamento);
    }

    @Transactional
    public void remove(UUID id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new FormaPagamentoNaoEncontradaException(id, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código %s não pode ser removida, pois está em uso", id));
        }
    }

    private Integer getLastCodInterno() {
        return formaPagamentoRepository.getLastCodInterno();
    }
}
