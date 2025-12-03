package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/por-nome")
    public List<Produto> listarPorNome(@RequestParam String nome) {
        return produtoService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public Produto listarPorId(@PathVariable Long id) {
        return produtoService.filtrarPorId(id);
    }

    @PostMapping
    public Produto inserir(@RequestBody Produto produto) {
        return produtoService.inserirOuAtualizar(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        var produtoAtual = produtoService.filtrarPorId(id);
        BeanUtils.copyProperties(produto, produtoAtual, "id", "dataCadastro");

        try {
            return produtoService.inserirOuAtualizar(produtoAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
