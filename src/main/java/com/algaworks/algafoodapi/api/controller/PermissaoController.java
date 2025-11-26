package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.service.PermissaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping("/listar")
    public List<Permissao> listar() {
        return permissaoService.buscarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            var permissao = permissaoService.buscarPorId(id);
            return ResponseEntity.ok(permissao.get());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/por-nome")
    public List<Permissao> buscarPorNome(@RequestParam String nome) {
        try {
            return permissaoService.filtroPorNome(nome);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> salvarNova(@RequestBody Permissao permissaoNova) {
        try {
            permissaoNova = permissaoService.inserirNova(permissaoNova);
            return ResponseEntity.status(HttpStatus.CREATED).body(permissaoNova);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {
        try {
            var permissaoAtual = permissaoService.buscarPorId(id);

            if (permissaoAtual.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");
            Permissao permissaoSalva = permissaoService.atualizar(permissaoAtual.get());
            return ResponseEntity.ok(permissaoSalva);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
