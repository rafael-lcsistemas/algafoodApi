package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodas() {
        try {
            return permissaoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todas as permissões");
        }
    }

    public List<Permissao> filtroPorNome(String nome) {
        try {
            return permissaoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar as permissões por nome");
        }
    }

    public Permissao filtrarPorId(Long id) {
        return permissaoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Permissão do código %d não encontrada", id)));
    }

    public Permissao inserirOuAtualizar(Permissao permissao) {

        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome da permissão inválido, por favor, verifique e tente novamente.");
        }

        if (permissao.getDescricao().isEmpty()) {
            throw new EntidadeIntegridadeException("Descrição da permissão inválida, por favor, verifique e tente novamente.");
        }

        return permissaoRepository.save(permissao);
    }
}
