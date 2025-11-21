package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.FormaPagamento;
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
    public List<FormaPagamento> listar(){
        try {
            formaPagamentoService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return formaPagamentoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            FormaPagamento formaPagamento = formaPagamentoService.findById(id);
            return ResponseEntity.ok(formaPagamento);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody FormaPagamento formaPagamento) {
        try {
            formaPagamento = formaPagamentoService.insert(formaPagamento);
            return ResponseEntity.status(201).body(formaPagamento);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamentoAtualizado) {
        try {
            FormaPagamento formaPagamentoParaAtualizar = formaPagamentoService.findById(id);
            BeanUtils.copyProperties(formaPagamentoAtualizado, formaPagamentoParaAtualizar, "id");
            FormaPagamento formaPagamentoSalvo = formaPagamentoService.update(formaPagamentoParaAtualizar);

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
            formaPagamentoService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
