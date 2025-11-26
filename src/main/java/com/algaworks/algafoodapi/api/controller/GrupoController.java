package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import com.algaworks.algafoodapi.domain.service.GrupoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping("/listar")
    public List<Grupo> filtrarTodas() {
        return grupoService.filtrarTodas();
    }

    @GetMapping("/por-nome")
    public List<Grupo> filtrarPorNome(@RequestParam String nome) {
        return grupoService.filtrarPorNome(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> filtrarPorId(@PathVariable Long id) {
        try {
            var grupo = grupoService.buscarPorId(id);
            return ResponseEntity.ok(grupo.get());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Grupo grupo) {
        try {
            var grupoNovo = grupoService.inserirOuAtualizar(grupo);
            return ResponseEntity.status(201).body(grupoNovo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Grupo grupo) {
        try {
            var grupoAtual = grupoService.buscarPorId(id);
            BeanUtils.copyProperties(grupo, grupoAtual.get(), "id");
            var grupoSalvo = grupoService.inserirOuAtualizar(grupoAtual.get());
            return ResponseEntity.status(201).body(grupoSalvo);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeIntegridadeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
