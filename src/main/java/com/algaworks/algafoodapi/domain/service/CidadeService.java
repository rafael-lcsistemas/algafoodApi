package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.entity.Cidade;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findAll() {
       try {
            return cidadeRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro inesperado ao buscar cidades");
        }
    }

    public Cidade findById(Long id) {
        if(id == null) {
            throw new EntidadeIntegridadeException("Código da cidade não pode ser nulo");
        }

        Cidade cidade = cidadeRepository.findById(id);

        if(cidade == null) {
            throw  new EntidadeNaoEncontradaException(String.format("Cidade do código %d não encontrado", id));
        }

        return cidade;
    }

    public Cidade insert(Cidade cidade) {
        return null;
    }

    public Cidade update(Cidade cidade) {
        return null;
    }

    public void delete(Long id) {

    }
}
