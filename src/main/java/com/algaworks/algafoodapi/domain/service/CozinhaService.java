package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> buscarTodas() {
        try {
            return cozinhaRepository.findAll();
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar todas as cozinhas");
        }
    }

    public List<Cozinha> filtrarPorNome(String nome) {
        try {
            return cozinhaRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar cozinhas por nome");
        }
    }

    public Cozinha filtrarPorId(UUID id) {
        return cozinhaRepository.findById(id).orElseThrow(() ->
                new CozinhaNaoEncontradaException(id));
    }

    @Transactional
    public Cozinha inserirOuAtualizar(Cozinha cozinha) {
        if (cozinha.getCodInterno() == null) {
            cozinha.setCodInterno(getLastCodInterno() + 1);
        }

        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public void remove(UUID id) {
        try {
            var cozinha = filtrarPorId(id);

            cozinhaRepository.deleteById(cozinha.getId());
            cozinhaRepository.flush();
        } catch (CozinhaNaoEncontradaException ex) {
            throw new NegocioException(ex.getMessage());
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(id, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %s não pode ser removida, pois está em uso", id));
        }
    }

    public Integer getLastCodInterno() {
        return cozinhaRepository.getLastCodInterno();
    }
}
