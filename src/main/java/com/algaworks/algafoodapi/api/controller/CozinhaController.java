package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> buscarTodas() {
        return cozinhaService.buscarTodas();
    }

    @GetMapping("/por-nome")
    public List<Cozinha> filtrarPorNome(@RequestParam String nome) {
        return cozinhaService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Cozinha filtrarPorId(@PathVariable Long id) {
        return cozinhaService.filtrarPorId(id);
    }

    @PostMapping
    public Cozinha inserirNova(@RequestBody Cozinha cozinha) {
        return cozinhaService.inserirOuAtualizar(cozinha);
    }

    @PutMapping("/{id}")
    public Cozinha update(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        var cozinhaAtual = cozinhaService.filtrarPorId(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaService.inserirOuAtualizar(cozinhaAtual);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cozinhaService.remove(id);
    }
}
