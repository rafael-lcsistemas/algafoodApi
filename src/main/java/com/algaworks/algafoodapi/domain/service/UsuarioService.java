package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.UsuarioNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoService grupoService;

    public List<Usuario> listarTodos() {
        try {
            return usuarioRepository.findAll();
        } catch (RuntimeException e) {
            throw new NegocioException("Erro ao buscar todos os restaurantes");
        }
    }

    public List<Usuario> filtrarPorNome(String nome) {
        try {
            return usuarioRepository.findByNomeContaining(nome);
        } catch (RuntimeException e) {
            throw new NegocioException("Erro ao buscar restaurantes por nome");
        }
    }

    public Usuario filtrarPorID(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradaException(id));
    }

    public Usuario iserirOuAtualizar(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new EntidadeIntegridadeException("Nome do usuário está inválido. Por favor, verifique e tente novamente.");
        }

        if (usuario.getAtivo() == null) {
            throw new EntidadeIntegridadeException("O status do usuário está inválido. Por favor, verifique e tente novamente.");
        }

        var existsGrupos = usuario.getGrupos().size() > 0;

        if (!existsGrupos) {
            throw new EntidadeIntegridadeException("É preciso informar pelo menos um grupo para este usuário");
        }

        try {
            List<Grupo> gruposValidados = new ArrayList<>();

            for (Grupo p : usuario.getGrupos()) {
                Grupo grp = grupoService.filtrarPorId(p.getId());
                gruposValidados.add(grp);
            }

            usuario.setGrupos(gruposValidados);
            return usuarioRepository.save(usuario);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        } catch (Exception e) {
            throw new NegocioException("Não foi possível salvar esse usuário. Por favor, verifique os dados e tente novamente.");
        }
    }
}
