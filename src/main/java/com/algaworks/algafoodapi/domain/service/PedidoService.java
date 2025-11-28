package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeIntegridadeException;
import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new RuntimeException("Erro inesperado ao buscar pedidos");
        }
    }

    public Optional<Pedido> filtrarPorID(Long id) {
        var pedido = pedidoRepository.findById(id);

        if (pedido.isEmpty()) {
            throw new EntidadeNaoEncontradaException(String.format("Pedido do código %d não encontrado", id));
        }

        return pedido;
    }

    public Pedido inserirOuAtualizar(Pedido pedido) {

        var usuario = usuarioService.filtrarPorID(pedido.getUsuarioPedido().getId())
                .orElseThrow(() -> new EntidadeIntegridadeException("Usuário inválido."));

        var pagamento = formaPagamentoService.filtrarPorID(pedido.getFormaPagamento().getId())
                .orElseThrow(() -> new EntidadeIntegridadeException("Forma de pagamento inválida."));

        var restaurante = restauranteService.findById(pedido.getRestaurante().getId())
                .orElseThrow(() -> new EntidadeIntegridadeException("Restaurante inválido."));

        pedido.setUsuarioPedido(usuario);
        pedido.setFormaPagamento(pagamento);
        pedido.setRestaurante(restaurante);

        return pedidoRepository.save(pedido);
    }

}
