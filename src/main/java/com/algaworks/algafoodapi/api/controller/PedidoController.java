package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.api.model.response.pedido.PedidoResponse;
import com.algaworks.algafoodapi.api.model.response.pedido.PedidoResumeResponse;
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
    public List<PedidoResumeResponse> buscarTodos() {
        return genericResponseAssembler.toCollectionModel(pedidoService.buscarTodos(), PedidoResumeResponse.class);
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

    @PutMapping("/cancelar")
    public PedidoResponse cancelarPedido(@RequestParam Long id) {
        var pedido = pedidoService.cancelarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResponse.class);
    }

    @PutMapping("/confirmar")
    public PedidoResponse confirmarPedido(@RequestParam Long id) {
        var pedido = pedidoService.confirmarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResponse.class);
    }

    @PutMapping("/entregar")
    public PedidoResponse entregarPedido(@RequestParam Long id) {
        var pedido = pedidoService.entregarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResponse.class);
    }
}
