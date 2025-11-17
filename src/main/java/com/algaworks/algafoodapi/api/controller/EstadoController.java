package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/listar")
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("{id}")
    public Estado buscarPorId(@PathVariable Long id) {
        return estadoRepository.findById(id);
    }

    @PostMapping
    public Estado inserirOrUpdate(@RequestBody Estado estado) {
        return estadoRepository.insertOrUpdate(estado);
    }
}
