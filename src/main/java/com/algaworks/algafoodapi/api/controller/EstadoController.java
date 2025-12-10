package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.EstadoInput;
import com.algaworks.algafoodapi.api.model.response.EstadoResponse;
import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.algaworks.algafoodapi.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<EstadoResponse> filtrarTodas() {
        return genericResponseAssembler.toCollectionModel(estadoService.filtrarTodas(), EstadoResponse.class);
    }

    @GetMapping("/por-nome")
    public List<EstadoResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(estadoService.filtrarPorNome(nome), EstadoResponse.class);
    }

    @GetMapping("{id}")
    public EstadoResponse buscarPorId(@PathVariable Long id) {
        return genericResponseAssembler.toModel(estadoService.filtrarPorID(id), EstadoResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstadoResponse inserir(@RequestBody @Valid EstadoInput estadoInput) {
        var estado = genericInputAssembler.toEntity(estadoInput, Estado.class);
        return genericResponseAssembler.toModel(estadoService.inserirOuAtualizar(estado), EstadoResponse.class);
    }

    @PutMapping("/{id}")
    public EstadoResponse atualizar(@PathVariable Long id, @RequestBody @Valid EstadoInput estadoInput) {
        var estado = genericInputAssembler.toEntity(estadoInput, Estado.class);
        var estadoAtual = estadoService.filtrarPorID(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return genericResponseAssembler.toModel(estadoService.inserirOuAtualizar(estadoAtual), EstadoResponse.class);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        estadoService.remove(id);
    }
}
