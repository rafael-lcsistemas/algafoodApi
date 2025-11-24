package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> findAll() {
        return cozinhaRepository.findAll();
    }

    public Optional<Cozinha> findById(Long id) {
        if(id == null) {
            throw new EntidadeIntegridadeException("ID da cozinha não pode ser nulo");
        }

        try {
            return cozinhaRepository.findById(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada", id));
        }
    }

    public Cozinha insert(Cozinha cozinha) {
        if (cozinha.getNome().isEmpty() || cozinha.getNome() == null) {
            throw new RuntimeException("Cozinha não pode ter o nome vazio ou nulo");
        }

        try {
            return cozinhaRepository.save(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possivel salvar cozinha, verifique os dados e tente novamente");
        }
    }

    public Cozinha update(Cozinha cozinha) {
        if (cozinha.getNome().isEmpty() || cozinha.getNome() == null) {
            throw new RuntimeException("Cozinha não pode ter o nome vazio ou nulo");
        }

        try {
            return cozinhaRepository.save(cozinha);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não foi possivel atualizar os dados de cozinha, verifique os dados e tente novamente");
        }
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada", id));
        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
