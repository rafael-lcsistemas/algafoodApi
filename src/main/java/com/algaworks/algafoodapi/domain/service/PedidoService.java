package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.exceptions.NegocioException;
import com.algaworks.algafoodapi.domain.exceptions.PedidoNaoEncontradaException;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            throw new NegocioException("Erro inesperado ao buscar todos os pedidos");
        }
    }

    public Pedido filtrarPorID(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new PedidoNaoEncontradaException(id));
    }

    public Pedido inserirOuAtualizar(Pedido pedido) {
        try {
            Usuario usuario = usuarioService.filtrarPorID(
                    pedido.getUsuarioPedido().getId()
            );

            FormaPagamento pagamento = formaPagamentoService.filtrarPorID(
                    pedido.getFormaPagamento().getId()
            );

            Restaurante restaurante = restauranteService.filtrarPorID(
                    pedido.getRestaurante().getId()
            );

            pedido.setUsuarioPedido(usuario);
            pedido.setFormaPagamento(pagamento);
            pedido.setRestaurante(restaurante);

            return pedidoRepository.save(pedido);

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        } catch (Exception e) {
            throw new NegocioException(
                    "Erro inesperado ao salvar pedido. Por favor, verifique os dados e tente novamente.", e
            );
        }
    }


    public Pedido cancelarPedido(Long id) {
        var pedido = filtrarPorID(id);

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setDatahoraCancelamento(LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }
}
