package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Cozinha> buscarTodas() {
        try {
            return cozinhaRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todas as cozinhas");
        }
    }

    public List<Cozinha> filtrarPorNome(String nome) {
        try {
            return cozinhaRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar cozinhas por nome");
        }
    }

    public Optional<Cozinha> filtrarPorId(Long id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if(cozinha.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não encontrada", id));
        }

        return cozinha;
    }

    public Cozinha inserirOuAtualizar(Cozinha cozinha) {
        if (cozinha.getNome().isEmpty() || cozinha.getNome() == null) {
            throw new EntidadeIntegridadeException("Cozinha não pode ter o nome vazio ou nulo");
        }

        if (cozinha.getAtivo() == null) {
            throw new EntidadeIntegridadeException("Necessario informar o status da cozinha");
        }

        try {
            return cozinhaRepository.save(cozinha);
        } catch (EntidadeIntegridadeException e) {
            throw new EntidadeIntegridadeException("Não foi possivel salvar cozinha, verifique os dados e tente novamente");
        }
    }

    public void remove(Long id) {
        try {
            cozinhaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Cozinha de código %d não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
