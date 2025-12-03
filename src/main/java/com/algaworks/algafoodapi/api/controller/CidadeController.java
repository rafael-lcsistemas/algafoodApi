package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Cidade;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/listar")
    public List<Cidade> filtrarTodas() {
        return cidadeService.filtrarTodas();
    }

    @GetMapping("/por-nome")
    public List<Cidade> filtrarPorNome(@RequestParam String nome) {
        return cidadeService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Cidade filtrarPorId(@PathVariable Long id) {
        return cidadeService.filtrarPorId(id);
    }

    @PostMapping
    public Cidade inserir(@RequestBody Cidade cidade) {
        return cidadeService.inserirOuAtualizar(cidade);
    }

    @PutMapping("/{id}")
    public Cidade atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        Cidade cidadeAtual = cidadeService.filtrarPorId(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return cidadeService.inserirOuAtualizar(cidadeAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remove(id);
    }
}
