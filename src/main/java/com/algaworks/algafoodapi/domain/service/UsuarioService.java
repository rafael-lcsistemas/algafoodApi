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
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os restaurantes");
        }
    }

    public List<Usuario> filtrarPorNome(String nome) {
        try {
            return usuarioRepository.findByNomeContaining(nome);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar restaurantes por nome");
        }
    }

    public Usuario filtrarPorID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Usuário não encontrado, código de busca %d", id)));
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
            Grupo grp = grupoService.filtrarPorId(p.getId());
            gruposValidados.add(grp);
        }

        usuario.setGrupos(gruposValidados);
        return usuarioRepository.save(usuario);
    }
}
