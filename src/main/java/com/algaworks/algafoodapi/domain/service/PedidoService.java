package com.algaworks.algafoodapi.domain.service;

import com.algaworks.algafoodapi.api.model.input.PedidoInput;
import com.algaworks.algafoodapi.domain.exceptions.*;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import com.algaworks.algafoodapi.domain.model.entity.pedido.PedidoDet;
import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.Restaurante;
import com.algaworks.algafoodapi.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    @Autowired
    private ProdutoService produtoService;

    private static final String MSG_STATUS_PEDIDO = "Status do pedido %d não pode ser alterado de %s para %s";

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

    @Transactional
    public Pedido novoPedido(Pedido pedido, PedidoInput input) {
        try {
            Usuario usuario = usuarioService.filtrarPorID(input.getIdusuario());
            Restaurante restaurante = restauranteService.filtrarPorID(input.getIdrestaurante());
            FormaPagamento pagamento = formaPagamentoService.filtrarPorID(input.getIdformapagamento());

            pedido.setUsuario(usuario);
            pedido.setRestaurante(restaurante);
            pedido.setFormaPagamento(pagamento);
            pedido.setStatusPedido(StatusPedido.CRIADO);
            pedido.setTaxaFrete(restaurante.getTaxaFrete());

            List<PedidoDet> itens = new ArrayList<>();
            BigDecimal total = BigDecimal.ZERO;
            BigDecimal valorDesconto = BigDecimal.ZERO;
            BigDecimal subtotal = BigDecimal.ZERO;

            for (PedidoInput.ItensPedidoResponse itemInput : input.getDet()) {

                Produto produto = produtoService.filtrarPorId(itemInput.getIdproduto());

                PedidoDet item = new PedidoDet();
                item.setPedido(pedido);
                item.setProduto(produto);
                item.setPreco(produto.getPreco());
                item.setQuantidade(itemInput.getQuantidade());
                item.setTotal(produto.getPreco().multiply(itemInput.getQuantidade()));
                item.setValorDesconto(itemInput.getValordesconto());
                item.setSubtotal(item.getTotal().subtract(item.getValorDesconto()));
                item.setObservacao(itemInput.getObservacao());

                total = total.add(item.getTotal());
                valorDesconto = valorDesconto.add(item.getValorDesconto());
                subtotal = subtotal.add(item.getSubtotal()).add(pedido.getTaxaFrete());

                itens.add(item);
            }

            pedido.setTotal(total);
            pedido.setValorDesconto(valorDesconto);
            pedido.setSubtotal(subtotal);
            pedido.setItensPedido(itens);

            pedido = pedidoRepository.save(pedido);

            return pedido;
        } catch (UsuarioNaoEncontradaException | RestauranteNaoEncontradaException |
                 FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Transactional
    public Pedido cancelarPedido(Long id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                    pedido.getId(), pedido.getStatusPedido().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setDatahoraCancelamento(OffsetDateTime.now());

        return  pedido;
    }

    @Transactional
    public Pedido confirmarPedido(Long id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() != StatusPedido.CRIADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                            pedido.getId(), pedido.getStatusPedido().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CONFIRMADO);
        pedido.setDatahoraConfirmacao(OffsetDateTime.now());

        return pedido;
    }

    @Transactional
    public Pedido entregarPedido(Long id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE
        || pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                    pedido.getId(), pedido.getStatusPedido().getDescricao(),  StatusPedido.ENTREGUE.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        pedido.setDatahoraEntrega(OffsetDateTime.now());

        return pedido;
    }
}
