package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoService permissaoService;

    public List<Grupo> filtrarTodas() {
        return grupoRepository.findAll();
    }

    public List<Grupo> filtrarPorNome(String nome) {
        return grupoRepository.findByNomeContaining(nome);
    }

    public Optional<Grupo> buscarPorId(Long id) {
        var grupo = grupoRepository.findById(id);

        if (grupo.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Grupo do código %d não encontado", id));
        }

        return grupo;
    }

    public Grupo inserirOuAtualizar(Grupo grupo) {
        if (grupo.getNome() == null || grupo.getNome().trim().isEmpty()) {
            throw new EntidadeIntegridadeException(
                    "Descrição do grupo inválida. Por favor, verifique e tente novamente."
            );
        }

        List<Permissao> permissoesValidas = new ArrayList<>();

        for (Permissao p : grupo.getPermissoes()) {
            Permissao perm = permissaoService.buscarPorId(p.getId())
                    .orElseThrow(() -> new EntidadeIntegridadeException(
                            "Permissão não encontrada: ID " + p.getId()
                    ));

            permissoesValidas.add(perm);
        }

        grupo.setPermissoes(permissoesValidas);
        return grupoRepository.save(grupo);
    }
}
