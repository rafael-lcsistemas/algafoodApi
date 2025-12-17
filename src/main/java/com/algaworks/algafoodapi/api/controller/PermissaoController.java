package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.PermissaoInput;
import com.algaworks.algafoodapi.api.model.response.permissao.PermissaoResponse;
import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.algaworks.algafoodapi.domain.service.PermissaoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<PermissaoResponse> buscarTodas() {
        return genericResponseAssembler.toCollectionModel(permissaoService.buscarTodas(), PermissaoResponse.class);
    }

    @GetMapping("/por-nome")
    public List<PermissaoResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(permissaoService.filtroPorNome(nome), PermissaoResponse.class);
    }

    @GetMapping("/{id}")
    public PermissaoResponse filtrarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(permissaoService.filtrarPorId(id), PermissaoResponse.class);
    }

    @PostMapping
    public PermissaoResponse inserir(@RequestBody @Valid PermissaoInput permissaoInput) {
        var permissao = genericInputAssembler.toEntity(permissaoInput, Permissao.class);
        return genericResponseAssembler.toModel(permissaoService.inserirOuAtualizar(permissao), PermissaoResponse.class);
    }

    @PutMapping("/{id}")
    public PermissaoResponse atualizar(@PathVariable UUID id, @RequestBody @Valid PermissaoInput permissaoInput) {
        var permissao = genericInputAssembler.toEntity(permissaoInput, Permissao.class);
        var permissaoAtual = permissaoService.filtrarPorId(id);
        BeanUtils.copyProperties(permissao, permissaoAtual, "id", "codInterno");

        return genericResponseAssembler.toModel(permissaoService.inserirOuAtualizar(permissaoAtual), PermissaoResponse.class);
    }
}
