package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.service.FormaPagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping("/listar")
    public List<FormaPagamento> buscarTodas(){
        try {
            return formaPagamentoService.buscarTodas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/por-nome")
    public List<FormaPagamento> filtrarPorNome(@RequestParam String nome) {
        try {
            return formaPagamentoService.filtrarPorNome(nome);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> filtrarPorId(@PathVariable Long id) {
        try {
            var formaPagamento = formaPagamentoService.filtrarPorID(id);
            return ResponseEntity.ok(formaPagamento.get());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody FormaPagamento formaPagamento) {
        try {
            formaPagamento = formaPagamentoService.inserirOuAtualizar(formaPagamento);
            return ResponseEntity.status(201).body(formaPagamento);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamentoAtualizado) {
        try {
            var formaPagamentoParaAtualizar = formaPagamentoService.filtrarPorID(id);
            BeanUtils.copyProperties(formaPagamentoAtualizado, formaPagamentoParaAtualizar.get(), "id");
            var formaPagamentoSalvo = formaPagamentoService.inserirOuAtualizar(formaPagamentoParaAtualizar.get());

            return ResponseEntity.ok(formaPagamentoSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            formaPagamentoService.remove(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
