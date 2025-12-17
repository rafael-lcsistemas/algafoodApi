package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.ProdutoInput;
import com.algaworks.algafoodapi.api.model.response.ProdutoResponse;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.service.ProdutoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<ProdutoResponse> listarTodos() {
        return genericResponseAssembler.toCollectionModel(produtoService.listarTodos(), ProdutoResponse.class);
    }

    @GetMapping("/por-nome")
    public List<ProdutoResponse> listarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(produtoService.filtrarPorNome(nome), ProdutoResponse.class);
    }

    @GetMapping("/{id}")
    public ProdutoResponse listarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(produtoService.filtrarPorId(id), ProdutoResponse.class);
    }

    @PostMapping
    public ProdutoResponse inserir(@RequestBody @Valid ProdutoInput produtoInput) {
        var produto = genericInputAssembler.toEntity(produtoInput, Produto.class);
        return genericResponseAssembler.toModel(
                produtoService.inserirOuAtualizar(produto, produtoInput), ProdutoResponse.class);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(@PathVariable UUID id, @RequestBody @Valid ProdutoInput produtoInput) {
        var produto = genericInputAssembler.toEntity(produtoInput, Produto.class);
        var produtoAtual = produtoService.filtrarPorId(id);
        BeanUtils.copyProperties(produto, produtoAtual, "id", "dataCadastro", "codInterno");

        return genericResponseAssembler.toModel(
                produtoService.inserirOuAtualizar(produtoAtual, produtoInput), ProdutoResponse.class);
    }
}
