package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listar")
    public List<Pedido> buscarTodos() {
        return pedidoService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Pedido filtrarPorID(@PathVariable Long id) {
        return pedidoService.filtrarPorID(id);
    }

    @PostMapping
    public Pedido inserir(@RequestBody Pedido pedido) {
        return pedidoService.inserirOuAtualizar(pedido);
    }

    @PutMapping
    public Pedido cancelarPedido(@RequestParam Long id) {
        return pedidoService.cancelarPedido(id);
    }
}
