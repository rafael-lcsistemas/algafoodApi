package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.*;
import com.algaworks.algafoodapi.domain.model.entity.Estado;
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

    public Estado inserirOuAtualizar(Estado estado) {
        if (estado.getNome() == null || estado.getNome().isEmpty()) {
            throw new NegocioException("Estado com o nome inválido");
        }

        if (estado.getUf() == null || estado.getUf().isEmpty()) {
            throw new NegocioException("Estado com o a UF inválida");
        }

        try {
            return estadoRepository.save(estado);
        } catch (Exception e) {
            throw new NegocioException(
                    "Não foi possivel salvar esse Estado. Por favor, verifique os dados e tente novamente", e);
        }
    }

    public void remove(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradaException(id, e);
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));
        }
    }
}
