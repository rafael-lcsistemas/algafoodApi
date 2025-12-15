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
import java.util.Set;
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
        return grupoRepository.findById(id).orElseThrow(() -> new GrupoNaoEncontradaException(id));
    }

    @Transactional
    public Grupo inserirOuAtualizar(Grupo grupo, List<Long> idsPermissoes) {
        try {
            Set<Permissao> permissoesValidas = idsPermissoes.stream().map(id ->
                    permissaoService.filtrarPorId(id)).collect(Collectors.toSet());

            grupo.setPermissoes(permissoesValidas);

            return grupoRepository.save(grupo);
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void associar(Long idGrupo, Long idPermissao) {
        try {
            var grupo = filtrarPorId(idGrupo);
            var permissao = permissaoService.filtrarPorId(idPermissao);

            grupo.associarPermissao(permissao);
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public void desassociar(Long idGrupo, Long idPermissao) {
        try {
            var grupo = filtrarPorId(idGrupo);
            var permissao = permissaoService.filtrarPorId(idPermissao);

            grupo.desassociarPermissao(permissao);
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
