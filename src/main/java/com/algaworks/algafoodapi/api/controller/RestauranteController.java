package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping("/listar")
    public List<Restaurante> filtrarTodas() {
        return restauranteService.filtrarTodas();
    }

    @GetMapping("/por-nome")
    public List<Restaurante> filtrarNome(String nome) {
        return restauranteService.filtrarPorNome(nome);
    }

    @GetMapping("{id}")
    public Restaurante filtrarPorId(@PathVariable Long id) {
        return restauranteService.filtrarPorID(id);
    }

    @PostMapping
    public Restaurante inserir(@RequestBody Restaurante restaurante) {
        return restauranteService.inserirOuAtualizar(restaurante);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        var restauranteAtual = restauranteService.filtrarPorID(id);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "endereco", "dataCadastro");

        try {
            return restauranteService.inserirOuAtualizar(restauranteAtual);
        } catch (EntidadeIntegridadeException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
