package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> findAll() {
        try {
            return formaPagamentoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar formas de pagamento: " + e.getMessage());
        }
    }

    public FormaPagamento findById(Long id) {
        if (id == null) {
            throw new EntidadeIntegridadeException("ID da busca não pode ser nulo");
        }

        FormaPagamento formaPagamento = formaPagamentoRepository.findById(id);

        if (formaPagamento == null) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento do código %d não encontrada", id));
        }

        return formaPagamento;
    }

    public FormaPagamento insert(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento não pode ser nula");
        }

        if (formaPagamento.getNome().isEmpty() | formaPagamento.getNome() == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento nome inválido. Por favor, verifique e tente novamente");
        }

        return formaPagamentoRepository.insert(formaPagamento);
    }

    public FormaPagamento update(FormaPagamento formaPagamento) {
        if (formaPagamento == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento não pode ser nula");
        }

        if (formaPagamento.getNome().isEmpty() | formaPagamento.getNome() == null) {
            throw new EntidadeIntegridadeException("Forma de pagamento nome inválido. Por favor, verifique e tente novamente");
        }

        return formaPagamentoRepository.update(formaPagamento);
    }

    public void delete(Long id) {
        try {
            formaPagamentoRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento de código %d não encontrada", id));
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
