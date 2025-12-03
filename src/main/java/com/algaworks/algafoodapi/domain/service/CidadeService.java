package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.CidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.model.entity.Cidade;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoService estadoService;

    public List<Cidade> filtrarTodas() {
        try {
            return cidadeRepository.findAll();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro inesperado ao buscar todas as cidades");
        }
    }

    public List<Cidade> filtrarPorNome(String nome) {
        try {
            return cidadeRepository.findByNomeContaining(nome);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro inesperado ao buscar cidades por nome");
        }
    }

    public Cidade filtrarPorId(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() ->
                new CidadeNaoEncontradaException(id));
    }

    public Cidade inserirOuAtualizar(Cidade cidade) {
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome da Cidade está inválido, por favor, verifique e tente novamente");
        }

        if (cidade.getEstado() == null || cidade.getEstado().getId() == null) {
            throw new EntidadeIntegridadeException("É necessário informar um Estado para a Cidade");
        }

        var estado = estadoService.filtrarPorID(cidade.getEstado().getId());
        cidade.setEstado(estado);

        try {
            return cidadeRepository.save(cidade);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel salvar essa Cidade. Por favor, verifique os dados e tente novamente");
        }
    }

    public void remove(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cidade de código %d não pode ser removida, pois está em uso", id));
        }
    }
}
