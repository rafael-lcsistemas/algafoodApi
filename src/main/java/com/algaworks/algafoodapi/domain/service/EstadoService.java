package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EstadoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> filtrarTodas() {
        try {
            return estadoRepository.findAll();
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar todos os Estados");
        }
    }

    public List<Estado> filtrarPorNome(String nome) {
        try {
            return estadoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar os Estados por nome");
        }
    }

    public Estado filtrarPorID(Long id) {
        return estadoRepository.findById(id).orElseThrow(() ->
                new EstadoNaoEncontradaException(id));
    }

    @Transactional
    public Estado inserirOuAtualizar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remove(Long id) {
        try {
            estadoRepository.deleteById(id);
            estadoRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradaException(id, e);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
