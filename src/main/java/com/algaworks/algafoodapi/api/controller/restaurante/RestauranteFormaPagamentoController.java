package com.algaworks.algafoodapi.api.controller.restaurante;

import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.response.FormaPagamentoResponse;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/formas-pagamento")
public class RestauranteFormaPagamentoController {

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @GetMapping
    public List<FormaPagamentoResponse> listarFormasPagamentoByIdRestaurante(@PathVariable UUID idRestaurante) {
        var restaurante = restauranteService.filtrarPorID(idRestaurante);
        List<FormaPagamento> listFormasPagamento = new ArrayList<>(restaurante.getFormasPagamento());
        return genericResponseAssembler.toCollectionModel(listFormasPagamento, FormaPagamentoResponse.class);
    }

    @DeleteMapping("/{idFormaPagamento}")
    public void desassociarFormaPagamento(
            @PathVariable UUID idRestaurante, @PathVariable UUID idFormaPagamento) {
        restauranteService.desassociarFormaPagamentoToRestaurante(idRestaurante, idFormaPagamento);
    }

    @PutMapping("/{idFormaPagamento}")
    public void asassociarFormaPagamento(
            @PathVariable UUID idRestaurante, @PathVariable UUID idFormaPagamento) {
        restauranteService.asassociarFormaPagamentoToRestaurante(idRestaurante, idFormaPagamento);
    }
}
