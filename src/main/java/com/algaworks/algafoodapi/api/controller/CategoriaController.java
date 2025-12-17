package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.CategoriaInput;
import com.algaworks.algafoodapi.api.model.response.categoria.CategoriaResponse;
import com.algaworks.algafoodapi.domain.model.entity.Categoria;
import com.algaworks.algafoodapi.domain.service.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping
    public List<CategoriaResponse> buscarTodos() {
        return genericResponseAssembler.toCollectionModel(categoriaService.listarTodas(), CategoriaResponse.class);
    }

    @GetMapping("/por-nome")
    public List<CategoriaResponse> buscarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(categoriaService.listarPorNome(nome), CategoriaResponse.class);
    }

    @GetMapping("/{id}")
    public CategoriaResponse buscarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(categoriaService.buscarPorId(id), CategoriaResponse.class);
    }

    @PostMapping
    public CategoriaResponse salvar(@RequestBody @Valid CategoriaInput input) {
        var categoria = genericInputAssembler.toEntity(input, Categoria.class);
        return genericResponseAssembler.toModel(categoriaService.inserirOuAtualizar(categoria), CategoriaResponse.class);
    }

    @PutMapping("/{id}")
    public CategoriaResponse atualizar(@PathVariable UUID id, @RequestBody @Valid CategoriaInput input) {
        var categoria = genericInputAssembler.toEntity(input, Categoria.class);
        var categoriaAtual = categoriaService.buscarPorId(id);
        BeanUtils.copyProperties(categoria, categoriaAtual, "id", "codInterno", "datahoraCadastro");

        return genericResponseAssembler.toModel(categoriaService.inserirOuAtualizar(categoriaAtual), CategoriaResponse.class);
    }
}
