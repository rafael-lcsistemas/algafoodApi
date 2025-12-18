package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.assembler.GenericInputAssembler;
import com.algaworks.algafoodapi.api.assembler.GenericResponseAssembler;
import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.api.model.response.pedido.PedidoResponse;
import com.algaworks.algafoodapi.api.model.response.pedido.PedidoResumeResponse;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.service.PedidoService;
import com.algaworks.algafoodapi.infrastructure.repository.specification.PedidoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private GenericResponseAssembler genericResponseAssembler;

    @Autowired
    private GenericInputAssembler genericInputAssembler;

    @GetMapping
    public Page<PedidoResponse> findByFilters(PedidoSpecification specification, Pageable pageable) {
        Page<Pedido> pedidoPage = pedidoService.findByFilters(specification, pageable);

        return new PageImpl<>(
                genericResponseAssembler.toCollectionModel(pedidoPage.getContent(), PedidoResponse.class),
                pageable, pedidoPage.getTotalElements()
        );
    }

    @GetMapping("/listar")
    public List<PedidoResumeResponse> buscarTodos() {
        return genericResponseAssembler.toCollectionModel(pedidoService.buscarTodos(), PedidoResumeResponse.class);
    }

    @GetMapping("/{id}")
    public PedidoResponse filtrarPorID(@PathVariable UUID id) {
        return genericResponseAssembler.toModel(pedidoService.filtrarPorID(id), PedidoResponse.class);
    }

    @PostMapping
    public PedidoResponse inserir(@RequestBody @Valid PedidoInput pedidoInput) {
        var pedido = genericInputAssembler.toEntity(pedidoInput, Pedido.class);
        return genericResponseAssembler.toModel(pedidoService.novoPedido(pedido, pedidoInput), PedidoResponse.class);
    }

    @PutMapping("/cancelar")
    public PedidoResumeResponse cancelarPedido(@RequestParam UUID id) {
        var pedido = pedidoService.cancelarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResumeResponse.class);
    }

    @PutMapping("/confirmar")
    public PedidoResumeResponse confirmarPedido(@RequestParam UUID id) {
        var pedido = pedidoService.confirmarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResumeResponse.class);
    }

    @PutMapping("/entregar")
    public PedidoResumeResponse entregarPedido(@RequestParam UUID id) {
        var pedido = pedidoService.entregarPedido(id);
        return genericResponseAssembler.toModel(pedido, PedidoResumeResponse.class);
    }
}
