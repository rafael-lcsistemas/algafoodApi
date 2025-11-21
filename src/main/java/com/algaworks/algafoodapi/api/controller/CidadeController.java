package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Cidade;
import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/listar")
    public List<Cidade> listar() {
        try {
            return cidadeService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Cidade cidade = cidadeService.findById(id);
            return ResponseEntity.ok(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Cidade cidade) {
        try {
            Cidade cidadeNova = cidadeService.insert(cidade);
            return ResponseEntity.status(201).body(cidadeNova);
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidadeAtualizada) {
        try {
            Cidade cidadeAtual = cidadeService.findById(id);
            BeanUtils.copyProperties(cidadeAtualizada, cidadeAtual, "id");
            Cidade cidadeSalva = cidadeService.update(cidadeAtual);

            return ResponseEntity.ok(cidadeSalva);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cidadeService.delete(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
