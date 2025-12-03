package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.*;
import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Cozinha filtrarPorId(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(() ->
                new CozinhaNaoEncontradaException(id));
    }

    public Cozinha inserirOuAtualizar(Cozinha cozinha) {
        if (cozinha.getNome().isEmpty() || cozinha.getNome() == null) {
            throw new NegocioException("Cozinha não pode ter o nome vazio ou nulo");
        }

        if (cozinha.getAtivo() == null) {
            throw new NegocioException("Necessario informar o status da cozinha");
        }

        try {
            return cozinhaRepository.save(cozinha);
        } catch (Exception e) {
            throw new NegocioException("Não foi possivel salvar cozinha, verifique os dados e tente novamente");
        }
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException(id, e);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
