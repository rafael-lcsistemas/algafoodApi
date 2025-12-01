package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/listar")
    public List<Grupo> filtrarTodas() {
        return grupoService.filtrarTodas();
    }

    @GetMapping("/por-nome")
    public List<Grupo> filtrarPorNome(@RequestParam String nome) {
        return grupoService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Grupo filtrarPorId(@PathVariable Long id) {
        return grupoService.filtrarPorId(id);
    }

    @PostMapping
    public Grupo inserir(@RequestBody Grupo grupo) {
        return grupoService.inserirOuAtualizar(grupo);
    }

    @PutMapping("/{id}")
    public Grupo atualizar(@PathVariable Long id, @RequestBody Grupo grupo) {
        var grupoAtual = grupoService.filtrarPorId(id);
        BeanUtils.copyProperties(grupo, grupoAtual, "id");
        return grupoService.inserirOuAtualizar(grupoAtual);
    }
}
