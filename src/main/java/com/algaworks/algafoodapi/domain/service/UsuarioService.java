package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.GrupoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.UsuarioNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public Usuario iserirOuAtualizar(Usuario usuario, List<Long> idsGrupos) {

        try {
            List<Grupo> gruposValidados = idsGrupos.stream().map(id ->
                    grupoService.filtrarPorId(id)).collect(Collectors.toList());

            usuario.setGrupos(gruposValidados);

            return usuarioRepository.save(usuario);
        } catch (GrupoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
