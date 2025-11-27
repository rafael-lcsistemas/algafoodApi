package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> buscarTodas() {
        try {
            return cozinhaService.buscarTodas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/por-nome")
    public List<Cozinha> filtrarPorNome(@RequestParam String nome) {
        try {
            return cozinhaService.filtrarPorNome(nome);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> filtrarPorId(@PathVariable Long id) {
        try {
            var cozinha = cozinhaService.filtrarPorId(id);
            return ResponseEntity.ok(cozinha.get());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> inserirNova(@RequestBody Cozinha cozinha) {
        try {
            var cozinhaSalva = cozinhaService.inserirOuAtualizar(cozinha);
            return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        try {
            var cozinhaAtual = cozinhaService.filtrarPorId(id);
            BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha cozinhaSalva = cozinhaService.inserirOuAtualizar(cozinhaAtual.get());
            return ResponseEntity.ok(cozinhaSalva);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cozinhaService.remove(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
