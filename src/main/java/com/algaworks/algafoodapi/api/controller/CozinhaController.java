package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
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

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping("/listar")
    public List<Cozinha> listar() {
        return cozinhaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
        try {
            Cozinha cozinha = cozinhaService.findById(id);
            return ResponseEntity.ok(cozinha);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha inserirNova(@RequestBody Cozinha cozinha) {
        return cozinhaService.insert(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cozinha cozinhaAtualizada) {
        try {
            Cozinha cozinhaAtual = cozinhaService.findById(id);
            BeanUtils.copyProperties(cozinhaAtualizada, cozinhaAtual, "id");
            Cozinha cozinhaSalva = cozinhaService.update(cozinhaAtual);

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
