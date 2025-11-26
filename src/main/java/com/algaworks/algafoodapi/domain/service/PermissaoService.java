package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodas() {
        return permissaoRepository.findAll();
    }

    public List<Permissao> filtroPorNome(String nome) {
        return permissaoRepository.findByNomeContaining(nome);
    }

    public Optional<Permissao> buscarPorId(Long id) {
        Optional<Permissao> permissao = permissaoRepository.findById(id);

        if (permissao.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Permissão do código %d não encontrada", id));
        }

        return permissao;
    }

    public Permissao inserirNova(Permissao permissao) {
        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome da permissão inválido, por favor, verifique e tente novamente.");
        }

        if (permissao.getDescricao().isEmpty()) {
            throw new EntidadeIntegridadeException("Descrição da permissão inválida, por favor, verifique e tente novamente.");
        }

        return permissaoRepository.save(permissao);
    }

    public Permissao atualizar(Permissao permissao) {
        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome da permissão inválido, por favor, verifique e tente novamente.");
        }

        if (permissao.getDescricao().isEmpty()) {
            throw new EntidadeIntegridadeException("Descrição da permissão inválida, por favor, verifique e tente novamente.");
        }

        return permissaoRepository.save(permissao);
    }

}
