package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.CozinhaInput;
import com.algaworks.algafoodapi.api.model.response.cozinha.CozinhaResponse;
import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<CozinhaResponse> buscarTodas() {
        return genericResponseAssembler.toCollectionModel(cozinhaService.buscarTodas(), CozinhaResponse.class);
    }

    @GetMapping("/por-nome")
    public List<CozinhaResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(cozinhaService.filtrarPorNome(nome), CozinhaResponse.class);
    }

    @GetMapping("/{id}")
    public CozinhaResponse filtrarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(cozinhaService.filtrarPorId(id), CozinhaResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CozinhaResponse inserir(@RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = genericInputAssembler.toEntity(cozinhaInput, Cozinha.class);
        return genericResponseAssembler.toModel(cozinhaService.inserirOuAtualizar(cozinha), CozinhaResponse.class);
    }

    @PutMapping("/{id}")
    public CozinhaResponse atualizar(@PathVariable UUID id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        var cozinha = genericInputAssembler.toEntity(cozinhaInput, Cozinha.class);
        var cozinhaAtual = cozinhaService.filtrarPorId(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id", "codInterno");

        return genericResponseAssembler.toModel(cozinhaService.inserirOuAtualizar(cozinhaAtual), CozinhaResponse.class);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable UUID id) {
        cozinhaService.remove(id);
    }
}
