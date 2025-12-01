package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> filtrarTodas() {
        try {
            return estadoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todos os Estados");
        }
    }

    public List<Estado> filtrarPorNome(String nome) {
        try {
            return estadoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar os Estados por nome");
        }
    }

    public Estado filtrarPorID(Long id) {
        return estadoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Estado do código %d não encontrado", id)));
    }

    public Estado inserirOuAtualizar(Estado estado) {
        if (estado.getNome() == null || estado.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("Estado com o nome inválido");
        }

        if (estado.getUf().isEmpty() | estado.getUf() == null) {
            throw new EntidadeIntegridadeException("Estado com o a UF inválida");
        }

        return estadoRepository.save(estado);
    }

    public void remove(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", id));
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
