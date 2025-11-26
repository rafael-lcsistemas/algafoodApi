package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoService grupoService;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public List<Usuario> filtrarPorNome(String nome) {
        return usuarioRepository.findByNomeContaining(nome);
    }

    public Optional<Usuario> filtrarPorID(Long id) {
        var usuarioSearch = usuarioRepository.findById(id);

        if (usuarioSearch.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Usuário não encontrado, código de busca %d", id));
        }

        return usuarioSearch;
    }

    public Usuario iserirOuAtualizar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new EntidadeIntegridadeException(
                    "Nome do usuário inválido. Por favor, verifique e tente novamente."
            );
        }

        var existsGrupos = usuario.getGrupos().size() > 0;

        if (!existsGrupos) {
            throw new EntidadeIntegridadeException("É preciso informar pelo menos um grupo para este usuário");
        }

        List<Grupo> gruposValidados = new ArrayList<>();

        for (Grupo p : usuario.getGrupos()) {
            Grupo grp = grupoService.buscarPorId(p.getId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException(
                            "Grupo não encontrado: ID " + p.getId()
                    ));

            gruposValidados.add(grp);
        }

        usuario.setGrupos(gruposValidados);
        return usuarioRepository.save(usuario);
    }
}
