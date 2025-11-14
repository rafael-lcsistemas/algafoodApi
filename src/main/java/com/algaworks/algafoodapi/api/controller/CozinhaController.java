package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("/listar")
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cozinha buscarPorId(@PathVariable Long id) {
        return cozinhaRepository.findById(id);
    }

    @PostMapping
    public Cozinha inserirOrUpdate(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.insertOrUpdate(cozinha);
    }

    @DeleteMapping
    public void deletar(Long id) {
        cozinhaRepository.delete(id);
    }
}
