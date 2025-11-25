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

    public List<Estado> findAll() {
        try {
            return estadoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar estados");
        }
    }

    public Estado findById(Long id) {
        if (id == null) {
            throw new EntidadeIntegridadeException("Código do estado não pode ser nulo");
        }

        Estado estado = estadoRepository.findById(id);

        if (estado == null) {
            throw new EntidadeNaoEncontradaException(String.format("Estado do código %d não encontrado", id));
        }

        return estado;
    }

    public Estado insert(Estado estado) {
        if (estado == null) {
            throw new EntidadeIntegridadeException("Entidade Estado está nula");
        }

        if (estado.getNome().isEmpty() | estado.getNome() == null) {
            throw new EntidadeIntegridadeException("Estado com o nome inválido");
        }

        if (estado.getUf().isEmpty() | estado.getUf() == null) {
            throw new EntidadeIntegridadeException("Estado com o uf inválida");
        }

        return estadoRepository.insert(estado);
    }

    public Estado update(Estado estado) {
        if (estado == null) {
            throw new EntidadeIntegridadeException("Entidade Estado não pode ser nula");
        }

        if (estado.getNome().isEmpty() | estado.getNome() == null) {
            throw new EntidadeIntegridadeException("Estado com o nome inválido");
        }

        if (estado.getUf().isEmpty() | estado.getUf() == null) {
            throw new EntidadeIntegridadeException("Estado com o nome inválido");
        }

        return estadoRepository.update(estado);
    }

    public void delete(Long id) {
        try {
            estadoRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", id));
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
