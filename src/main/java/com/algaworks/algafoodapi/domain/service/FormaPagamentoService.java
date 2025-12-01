package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> buscarTodas() {
        try {
            return formaPagamentoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todas as formas de pagamento");
        }
    }

    public List<FormaPagamento> filtrarPorNome(String nome) {
        try {
            return formaPagamentoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar formas de pagamento por nome");
        }
    }

    public FormaPagamento filtrarPorID(Long id) {
        return formaPagamentoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Forma de pagamento do código %d não encontrada", id)));
    }

    public FormaPagamento inserirOuAtualizar(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento não pode ser nula");
        }

        if (formaPagamento.getNome().isEmpty() | formaPagamento.getNome() == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento nome inválido. Por favor, verifique e tente novamente");
        }

        return formaPagamentoRepository.save(formaPagamento);
    }

    public void remove(Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento de código %d não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
