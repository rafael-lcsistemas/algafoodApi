package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.entity.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @GetMapping("/listar")
    public List<Restaurante> listar() {
        return restauranteRepository.findAll();
    }

    @GetMapping("{id}")
    public Restaurante buscarPorId(@PathVariable Long id) {
        return restauranteRepository.findById(id);
    }

    @PostMapping
    public Restaurante salvar(@RequestBody Restaurante restaurante) {
        return restauranteRepository.insertOrUpdate(restaurante);
    }
}
