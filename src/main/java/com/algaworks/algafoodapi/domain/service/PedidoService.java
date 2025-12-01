package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @Autowired
    private RestauranteService restauranteService;

    public List<Pedido> buscarTodos() {
        try {
            return pedidoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar todos os pedidos");
        }
    }

    public Pedido filtrarPorID(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Pedido do código %d não encontrado", id)));
    }

    public Pedido inserirOuAtualizar(Pedido pedido) {

        var usuario = usuarioService.filtrarPorID(pedido.getUsuarioPedido().getId());

        var pagamento = formaPagamentoService.filtrarPorID(pedido.getFormaPagamento().getId());

        var restaurante = restauranteService.filtrarPorID(pedido.getRestaurante().getId());

        pedido.setUsuarioPedido(usuario);
        pedido.setFormaPagamento(pagamento);
        pedido.setRestaurante(restaurante);

        return pedidoRepository.save(pedido);
    }

    public Pedido cancelarPedido(Long id) {
        var pedido = filtrarPorID(id);

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setDatahoraCancelamento(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }
}
