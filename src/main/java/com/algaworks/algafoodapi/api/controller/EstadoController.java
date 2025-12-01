package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listar")
    public List<Estado> filtrarTodas() {
        return estadoService.filtrarTodas();
    }

    @GetMapping("/por-nome")
    public List<Estado> filtrarPorNome(@RequestParam String nome) {
        return estadoService.filtrarPorNome(nome);
    }

    @GetMapping("{id}")
    public Estado buscarPorId(@PathVariable Long id) {
        return estadoService.filtrarPorID(id);
    }

    @PostMapping
    public Estado inserir(@RequestBody Estado estado) {
        return estadoService.inserirOuAtualizar(estado);
    }

    @PutMapping("/{id}")
    public Estado atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        var estadoAtual = estadoService.filtrarPorID(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoService.inserirOuAtualizar(estadoAtual);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        estadoService.remove(id);
    }
}
