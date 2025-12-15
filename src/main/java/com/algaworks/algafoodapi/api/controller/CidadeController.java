package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.CidadeInput;
import com.algaworks.algafoodapi.api.model.response.cidade.CidadeResponse;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Cidade;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<CidadeResponse> filtrarTodas() {
        return genericResponseAssembler.toCollectionModel(cidadeService.filtrarTodas(), CidadeResponse.class);
    }

    @GetMapping("/por-nome")
    public List<CidadeResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(cidadeService.filtrarPorNome(nome), CidadeResponse.class);
    }

    @GetMapping("/{id}")
    public CidadeResponse filtrarPorId(@PathVariable Long id) {
        return genericResponseAssembler.toModel(cidadeService.filtrarPorId(id), CidadeResponse.class);
    }

    @PostMapping
    public CidadeResponse inserir(@RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = genericInputAssembler.toEntity(cidadeInput, Cidade.class);

        try {
            return genericResponseAssembler.toModel(cidadeService.inserirOuAtualizar(cidade), CidadeResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CidadeResponse atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
        var cidade = genericInputAssembler.toEntity(cidadeInput, Cidade.class);
        Cidade cidadeAtual = cidadeService.filtrarPorId(id);
        BeanUtils.copyProperties(cidade, cidadeAtual, "id");

        try {
            return genericResponseAssembler.toModel(cidadeService.inserirOuAtualizar(cidadeAtual), CidadeResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        cidadeService.remove(id);
    }
}
