package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.api.model.response.PedidoResponse;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping("/listar")
    public List<PedidoResponse> buscarTodos() {
        return genericResponseAssembler.toCollectionModel(pedidoService.buscarTodos(), PedidoResponse.class);
    }

    @GetMapping("/{id}")
    public PedidoResponse filtrarPorID(@PathVariable Long id) {
        return genericResponseAssembler.toModel(pedidoService.filtrarPorID(id), PedidoResponse.class);
    }

    @PostMapping
    public PedidoResponse inserir(@RequestBody @Valid PedidoInput pedidoInput) {

        var pedido = genericInputAssembler.toEntity(pedidoInput, Pedido.class);

        try {
            return genericResponseAssembler.toModel(pedidoService.novoPedido(pedido, pedidoInput), PedidoResponse.class);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping
    public PedidoResponse cancelarPedido(@RequestParam Long id) {
        return genericResponseAssembler.toModel(pedidoService.cancelarPedido(id), PedidoResponse.class);
    }
}
