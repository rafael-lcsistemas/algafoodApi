package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Grupo inserir(@RequestBody @Valid Grupo grupo) {
        return grupoService.inserirOuAtualizar(grupo);
    }

    @PutMapping("/{id}")
    public Grupo atualizar(@PathVariable Long id, @RequestBody @Valid Grupo grupo) {
        var grupoAtual = grupoService.filtrarPorId(id);
        BeanUtils.copyProperties(grupo, grupoAtual, "id");

        try {
            return grupoService.inserirOuAtualizar(grupoAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage() + " ----> ");
        }
    }
}
