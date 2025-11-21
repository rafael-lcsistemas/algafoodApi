package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.entity.Cidade;
import com.algaworks.algafoodapi.domain.entity.Estado;
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

    @Autowired
    private EstadoService estadoService;

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
        if(cidade == null) {
            throw new EntidadeIntegridadeException("Erro inesperado. Cidade não pode ser nula.");
        }

        if(cidade.getNome().isEmpty() | cidade.getNome() == null) {
            throw new EntidadeIntegridadeException("Nome da Cidade está inválido, por favor, verifique e tente novamente");
        }

        Estado estado = estadoService.findById(cidade.getEstado().getId());

        if(estado == null) {
            throw new EntidadeIntegridadeException("Erro inesperado. Estado não pode ser nulo.");
        }

        cidade.setEstado(estado);

        return cidadeRepository.insert(cidade);
    }

    public Cidade update(Cidade cidade) {
        if(cidade == null) {
            throw new EntidadeIntegridadeException("Erro inesperado ao atualizar Cidade, verique os dados e tente novamente.");
        }

        if(cidade.getNome().isEmpty() | cidade.getNome() == null) {
            throw new EntidadeIntegridadeException("Cidade com o nome inválido, por favor, verifique e tente novamente.");
        }

        Estado estado = estadoService.findById(cidade.getEstado().getId());
        cidade.setEstado(estado);

        return cidadeRepository.update(cidade);
    }

    public void delete(Long id) {
        if(id == null) {
            throw new EntidadeIntegridadeException("Id não pode ser nulo.");
        }

        Cidade cidade = findById(id);

        if(cidade == null) {
            throw new EntidadeNaoEncontradaException("Cidade não encontrada");
        }

        cidadeRepository.delete(cidade.getId());
    }
}
