package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.FabricanteInput;
import com.algaworks.algafoodapi.api.model.response.fabricante.FabricanteResponse;
import com.algaworks.algafoodapi.domain.model.entity.Fabricante;
import com.algaworks.algafoodapi.domain.service.FabricanteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/fabricantes")
public class FabricanteController {

    @Autowired
    private FabricanteService fabricanteService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping
    public List<FabricanteResponse> buscarTodos() {
        return genericResponseAssembler.toCollectionModel(fabricanteService.listarTodos(), FabricanteResponse.class);
    }

    @GetMapping("/por-nome")
    public List<FabricanteResponse> buscarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(fabricanteService.listarPorNome(nome), FabricanteResponse.class);
    }

    @GetMapping("/{id}")
    public FabricanteResponse buscarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(fabricanteService.buscarPorId(id), FabricanteResponse.class);
    }

    @PostMapping
    public FabricanteResponse salvar(@RequestBody @Valid FabricanteInput input) {
        var fabricante = genericInputAssembler.toEntity(input, Fabricante.class);
        return genericResponseAssembler.toModel(fabricanteService.inserirOuAtualizar(fabricante), FabricanteResponse.class);
    }

    @PutMapping("/{id}")
    public FabricanteResponse atualizar(@PathVariable UUID id, @RequestBody @Valid FabricanteInput input) {
        var fabricante = genericInputAssembler.toEntity(input, Fabricante.class);
        var fabricanteAtual = fabricanteService.buscarPorId(id);
        BeanUtils.copyProperties(fabricante, fabricanteAtual, "id", "codInterno", "datahoraCadastro");

        return genericResponseAssembler.toModel(fabricanteService.inserirOuAtualizar(fabricanteAtual), FabricanteResponse.class);
    }
}
