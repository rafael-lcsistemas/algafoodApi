package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping("/listar")
    public List<FormaPagamento> buscarTodas() {
        return formaPagamentoService.buscarTodas();
    }

    @GetMapping("/por-nome")
    public List<FormaPagamento> filtrarPorNome(@RequestParam String nome) {
        return formaPagamentoService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public FormaPagamento filtrarPorId(@PathVariable Long id) {
        return formaPagamentoService.filtrarPorID(id);
    }

    @PostMapping
    public FormaPagamento inserir(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.inserirOuAtualizar(formaPagamento);
    }

    @PutMapping("/{id}")
    public FormaPagamento atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        var formaPagamentoAtual = formaPagamentoService.filtrarPorID(id);
        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
        return formaPagamentoService.inserirOuAtualizar(formaPagamentoAtual);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        formaPagamentoService.remove(id);
    }
}
