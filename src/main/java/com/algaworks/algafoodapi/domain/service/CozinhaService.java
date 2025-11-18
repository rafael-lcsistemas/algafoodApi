package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    public Cozinha findById(Long id) {
        try {
            Cozinha cozinha = cozinhaRepository.findById(id);

            if (cozinha == null) {
                throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada.", id));
            }
            return cozinhaRepository.findById(id);
        } catch (EntityNotFoundException e) {
            throw new EntidadeNaoEncontradaException(e.getMessage());
        }
    }

    public Cozinha insert(Cozinha cozinha) {
        try {
            if (cozinha.getNome() == null) {
                throw new DataIntegrityViolationException("Não foi possivel salvar cozinha, verifique os dados e tente novamente");
            }

            return cozinhaRepository.insert(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possivel salvar cozinha, verifique os dados e tente novamente");
        }
    }

    public Cozinha update(Cozinha cozinha) {
        try {

            if(cozinha.getNome().isEmpty() | cozinha.getNome() == null) {
                throw new RuntimeException("Cozinha não pode ter o nome vazio ou nulo");
            }

            return cozinhaRepository.update(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possivel atualizar os dados de cozinha, verifique os dados e tente novamente");
        }
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada", id));
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
