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
    public List<Permissao> buscarTodas() {
        return permissaoService.buscarTodas();
    }

    @GetMapping("/por-nome")
    public List<Permissao> filtrarPorNome(@RequestParam String nome) {
        return permissaoService.filtroPorNome(nome);
    }

    @GetMapping("/{id}")
    public Permissao filtrarPorId(@PathVariable Long id) {
        return permissaoService.filtrarPorId(id);
    }

    @PostMapping
    public Permissao inserir(@RequestBody Permissao permissao) {
        return permissaoService.inserirOuAtualizar(permissao);
    }

    @PutMapping("/{id}")
    public Permissao atualizar(@PathVariable Long id, @RequestBody Permissao permissao) {
        var permissaoAtual = permissaoService.filtrarPorId(id);
        BeanUtils.copyProperties(permissao, permissaoAtual, "id");
        return permissaoService.inserirOuAtualizar(permissaoAtual);
    }
}
