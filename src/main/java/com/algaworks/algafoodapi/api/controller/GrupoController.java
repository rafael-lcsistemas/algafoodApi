package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.GrupoInput;
import com.algaworks.algafoodapi.api.model.response.GrupoResponse;
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

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<GrupoResponse> filtrarTodas() {
        return genericResponseAssembler.toCollectionModel(grupoService.filtrarTodas(), GrupoResponse.class);
    }

    @GetMapping("/por-nome")
    public List<GrupoResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(grupoService.filtrarPorNome(nome), GrupoResponse.class);
    }

    @GetMapping("/{id}")
    public GrupoResponse filtrarPorId(@PathVariable Long id) {
        return genericResponseAssembler.toModel(grupoService.filtrarPorId(id), GrupoResponse.class);
    }

    @PostMapping
    public GrupoResponse inserir(@RequestBody @Valid GrupoInput grupoInput) {
        var grupo = genericInputAssembler.toEntity(grupoInput, Grupo.class);
        return genericResponseAssembler.toModel(
                grupoService.inserirOuAtualizar(grupo, grupoInput.getIdsPermissoes()), GrupoResponse.class);
    }

    @PutMapping("/{id}")
    public GrupoResponse atualizar(@PathVariable Long id, @RequestBody @Valid GrupoInput grupoInput) {
        var grupo = genericInputAssembler.toEntity(grupoInput, Grupo.class);
        var grupoAtual = grupoService.filtrarPorId(id);
        BeanUtils.copyProperties(grupo, grupoAtual, "id");

        try {
            return genericResponseAssembler.toModel(
                    grupoService.inserirOuAtualizar(grupoAtual, grupoInput.getIdsPermissoes()), GrupoResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
