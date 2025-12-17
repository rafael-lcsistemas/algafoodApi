package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.FormaPagamentoInput;
import com.algaworks.algafoodapi.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<FormaPagamentoResponse> buscarTodas() {
        return genericResponseAssembler.toCollectionModel(
                formaPagamentoService.buscarTodas(), FormaPagamentoResponse.class);
    }

    @GetMapping("/por-nome")
    public List<FormaPagamentoResponse> filtrarPorNome(@RequestParam String nome) {
        return genericResponseAssembler.toCollectionModel(
                formaPagamentoService.filtrarPorNome(nome), FormaPagamentoResponse.class);
    }

    @GetMapping("/{id}")
    public FormaPagamentoResponse filtrarPorId(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(formaPagamentoService.filtrarPorID(id), FormaPagamentoResponse.class);
    }

    @PostMapping
    public FormaPagamentoResponse inserir(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = genericInputAssembler.toEntity(formaPagamentoInput, FormaPagamento.class);
        return genericResponseAssembler.toModel(
                formaPagamentoService.inserirOuAtualizar(formaPagamento), FormaPagamentoResponse.class);
    }

    @PutMapping("/{id}")
    public FormaPagamentoResponse atualizar(@PathVariable UUID id, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        var formaPagamento = genericInputAssembler.toEntity(formaPagamentoInput, FormaPagamento.class);
        var formaPagamentoAtual = formaPagamentoService.filtrarPorID(id);
        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id", "codInterno");

        return genericResponseAssembler.toModel(
                formaPagamentoService.inserirOuAtualizar(formaPagamentoAtual), FormaPagamentoResponse.class);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable UUID id) {
        formaPagamentoService.remove(id);
    }
}
