package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.PermissaoNaoEncontradaException;
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

    public Permissao filtrarPorId(Long id) {
        return permissaoRepository.findById(id).orElseThrow(() ->
                new PermissaoNaoEncontradaException(id));
    }

    public Permissao inserirOuAtualizar(Permissao permissao) {

        if (permissao.getNome() == null || permissao.getNome().isEmpty()) {
            throw new NegocioException("Nome da permissão inválido, por favor, verifique e tente novamente.");
        }

        if (permissao.getDescricao() == null || permissao.getDescricao().isEmpty()) {
            throw new NegocioException("Descrição da permissão inválida, por favor, verifique e tente novamente.");
        }

        if (permissao.getAtivo() == null) {
            throw new NegocioException("Status da permissão inválida.Por favor, verifique e tente novamente.");
        }

        try {
            return permissaoRepository.save(permissao);
        } catch (Exception e) {
            throw new NegocioException(
                    "Não foi possivel salvar essa permissão. Por favor, verifique os dados e tente novamente", e);
        }
    }
}
