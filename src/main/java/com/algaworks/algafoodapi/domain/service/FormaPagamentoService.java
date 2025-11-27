package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> buscarTodas() {
        return formaPagamentoRepository.findAll();
    }

    public List<FormaPagamento> filtrarPorNome(String nome) {
        return formaPagamentoRepository.findByNomeContaining(nome);
    }

    public Optional<FormaPagamento> filtrarPorID(Long id) {
        if (id == null) {
            throw new EntidadeIntegridadeException("ID da busca não pode ser nulo");
        }

        var formaPagamento = formaPagamentoRepository.findById(id);

        if (formaPagamento.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento do código %d não encontrada", id));
        }

        return formaPagamento;
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
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(String.format("Forma de pagamento de código %d não encontrada", id));
        } catch (EntidadeEmUsoException e) {
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
