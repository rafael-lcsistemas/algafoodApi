package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.service.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarTodos() {
        try {
            return usuarioService.listarTodos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/por-nome")
    public List<Usuario> filtrarPorNome(@RequestParam String nome) {
        try {
            return usuarioService.filtrarPorNome(nome);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> filtrarPorID(@PathVariable Long id) {
        try {
            var usuario = usuarioService.filtrarPorID(id);
            return ResponseEntity.ok(usuario.get());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> inserirNovo(@RequestBody Usuario usuario) {
        try {
            var usuarioSalvo = usuarioService.iserirOuAtualizar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            var usuarioAtual = usuarioService.filtrarPorID(id);
            BeanUtils.copyProperties(usuario, usuarioAtual.get(), "id", "dataCadastro");
            var usuarioSalvo = usuarioService.iserirOuAtualizar(usuarioAtual.get());
            return ResponseEntity.ok().body(usuarioSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
