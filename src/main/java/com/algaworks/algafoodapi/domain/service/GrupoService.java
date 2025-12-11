package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.GrupoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.PermissaoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public List<Grupo> filtrarTodas() {
        try {
            return grupoRepository.findAll();
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar todos os grupos");
        }
    }

    public List<Grupo> filtrarPorNome(String nome) {
        try {
            return grupoRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new NegocioException("Erro ao buscar grupos por nome");
        }
    }

    public Grupo filtrarPorId(Long id) {
        return grupoRepository.findById(id).orElseThrow(() ->
                new GrupoNaoEncontradaException(id));
    }

    @Transactional
    public Grupo inserirOuAtualizar(Grupo grupo, List<Long> idsPermissoes) {
        try {
            List<Permissao> permissoesValidas = idsPermissoes.stream()
                    .map(id -> permissaoService.filtrarPorId(id)).collect(Collectors.toList());

            grupo.setPermissoes(permissoesValidas);

            return grupoRepository.save(grupo);
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
