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
import com.algaworks.algafoodapi.infrastructure.repository.specification.PedidoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

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

    private static final String MSG_STATUS_PEDIDO = "Status do pedido %s não pode ser alterado de %s para %s";

    public List<Pedido> buscarTodos() {
        try {
            return pedidoRepository.findAll();
        } catch (RuntimeException e) {
            throw new NegocioException("Erro inesperado ao buscar todos os pedidos");
        }
    }

    public Pedido filtrarPorID(UUID id) {
        return pedidoRepository.findById(id).orElseThrow(() ->
                new PedidoNaoEncontradaException(id));
    }

    @Transactional
    public Pedido novoPedido(Pedido pedido, PedidoInput input) {
        try {
            if (pedido.getCodInterno() == null) {
                pedido.setCodInterno(getLastCodInterno() + 1);
            }

            Usuario usuario = usuarioService.filtrarPorID(input.getIdusuario());
            Restaurante restaurante = restauranteService.filtrarPorID(input.getIdrestaurante());
            FormaPagamento pagamento = formaPagamentoService.filtrarPorID(input.getIdformapagamento());

            pedido.setUsuario(usuario);
            pedido.setRestaurante(restaurante);
            pedido.setFormaPagamento(pagamento);
            pedido.setTaxaFrete(restaurante.getTaxaFrete());

            pedido.setTotal(BigDecimal.ZERO);
            pedido.setValorDesconto(BigDecimal.ZERO);
            pedido.setSubtotal(BigDecimal.ZERO);

            BigDecimal total = BigDecimal.ZERO;
            BigDecimal valorDesconto = BigDecimal.ZERO;
            BigDecimal subtotal = BigDecimal.ZERO;

            for (PedidoInput.ItensPedidoResponse itemInput : input.getDet()) {

                Produto produto = produtoService.filtrarPorId(itemInput.getIdproduto());

                PedidoDet item = new PedidoDet();
                item.setProduto(produto);
                item.setPreco(produto.getPreco());
                item.setQuantidade(itemInput.getQuantidade());
                item.setTotal(produto.getPreco().multiply(itemInput.getQuantidade()));
                item.setValorDesconto(itemInput.getValordesconto());
                item.setSubtotal(item.getTotal().subtract(item.getValorDesconto()));
                item.setObservacao(itemInput.getObservacao());

                pedido.addItemPedido(item);

                total = total.add(item.getTotal());
                valorDesconto = valorDesconto.add(item.getValorDesconto());
                subtotal = subtotal.add(item.getSubtotal());
            }

            pedido.setTotal(total);
            pedido.setValorDesconto(valorDesconto);
            pedido.setSubtotal(subtotal.add(pedido.getTaxaFrete()));

            return pedidoRepository.save(pedido);
        } catch (UsuarioNaoEncontradaException |
                 RestauranteNaoEncontradaException |
                 FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    @Transactional
    public Pedido cancelarPedido(UUID id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                    pedido.getCodInterno(), pedido.getStatusPedido().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CANCELADO);
        pedido.setDatahoraCancelamento(OffsetDateTime.now());

        return pedido;
    }

    @Transactional
    public Pedido confirmarPedido(UUID id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() != StatusPedido.CRIADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                    pedido.getCodInterno(), pedido.getStatusPedido().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.CONFIRMADO);
        pedido.setDatahoraConfirmacao(OffsetDateTime.now());

        return pedido;
    }

    @Transactional
    public Pedido entregarPedido(UUID id) {
        var pedido = filtrarPorID(id);

        if (pedido.getStatusPedido() == StatusPedido.ENTREGUE
                || pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new NegocioException(String.format(MSG_STATUS_PEDIDO,
                    pedido.getCodInterno(), pedido.getStatusPedido().getDescricao(), StatusPedido.ENTREGUE.getDescricao()));
        }

        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        pedido.setDatahoraEntrega(OffsetDateTime.now());

        return pedido;
    }

    public Page<Pedido> findByFilters(PedidoSpecification specification, Pageable pageable) {
        return pedidoRepository.findAll(specification, pageable);
    }

    public Long getLastCodInterno() {
        return pedidoRepository.getLastCodInterno();
    }
}
