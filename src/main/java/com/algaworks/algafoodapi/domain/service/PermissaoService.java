package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.PermissaoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public List<Permissao> buscarTodas() {
        try {
            return permissaoRepository.findAll();
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar todas as permissões");
        }
    }

    public List<Permissao> filtroPorNome(String nome) {
        try {
            return permissaoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro inesperado ao buscar as permissões por nome");
        }
    }

    public Permissao filtrarPorId(UUID id) {
        return permissaoRepository.findById(id).orElseThrow(() ->
                new PermissaoNaoEncontradaException(id));
    }

    @Transactional
    public Permissao inserirOuAtualizar(Permissao permissao) {
        if(permissao.getCodInterno() == null) {
            permissao.setCodInterno(getLastCodInterno() + 1);
        }

        return permissaoRepository.save(permissao);
    }

    private Integer getLastCodInterno() {
        return permissaoRepository.getLastCodInterno();
    }
}
