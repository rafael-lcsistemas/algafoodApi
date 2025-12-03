package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class
UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/por-nome")
    public List<Usuario> filtrarPorNome(@RequestParam String nome) {
        return usuarioService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Usuario filtrarPorID(@PathVariable Long id) {
        return usuarioService.filtrarPorID(id);
    }

    @PostMapping
    public Usuario inserir(@RequestBody Usuario usuario) {
        return usuarioService.iserirOuAtualizar(usuario);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        var usuarioAtual = usuarioService.filtrarPorID(id);
        BeanUtils.copyProperties(usuario, usuarioAtual, "id", "dataCadastro");

        try {
            return usuarioService.iserirOuAtualizar(usuarioAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
