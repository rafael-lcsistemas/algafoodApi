package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafoodapi.api.assembler.RestauranteModelDisassembler;
import com.algaworks.algafoodapi.api.model.input.RestauranteInput;
import com.algaworks.algafoodapi.api.model.response.RestauranteResponse;
import com.algaworks.algafoodapi.domain.exceptions.CozinhaNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.FormaPagamentoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteModelDisassembler restauranteModelDisassembler;

    @GetMapping("/listar")
    public List<RestauranteResponse> filtrarTodas() {
        return restauranteModelAssembler.toColletionModel(restauranteService.filtrarTodas());
    }

    @GetMapping("/por-nome")
    public List<RestauranteResponse> filtrarNome(String nome) {
        return restauranteModelAssembler.toColletionModel(restauranteService.filtrarPorNome(nome));
    }

    @GetMapping("{id}")
    public RestauranteResponse filtrarPorId(@PathVariable Long id) {
        Restaurante restaurante = restauranteService.filtrarPorID(id);
        return restauranteModelAssembler.toModel(restaurante);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteResponse inserir(@RequestBody RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = restauranteModelDisassembler.toEntity(restauranteInput);
            return restauranteModelAssembler.toModel(restauranteService.inserirOuAtualizar(restaurante));
        } catch (CozinhaNaoEncontradaException | FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public RestauranteResponse atualizar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restauranteInput) {
        var restaurante = restauranteModelDisassembler.toEntity(restauranteInput);
        var restauranteAtual = restauranteService.filtrarPorID(id);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "endereco", "dataCadastro");

        try {
            return restauranteModelAssembler.toModel(restauranteService.inserirOuAtualizar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException | FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
