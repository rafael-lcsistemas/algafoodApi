package com.algaworks.algafoodapi.api.model.response;

import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class PedidoResponse {

    private Long id;
    private BigDecimal total;
    private BigDecimal taxaFrete;
    private BigDecimal valorDesconto;
    private BigDecimal subtotal;
    private StatusPedido statusPedido;
    private OffsetDateTime datahoraPedido;
    private OffsetDateTime datahoraConfirmacao;
    private OffsetDateTime datahoraCancelamento;
    private OffsetDateTime datahoraEntrega;
    private UsuarioPedidoResponse usuarioPedido;
    private RestaurantePedidoResponse restaurante;
    private FormaPagamentoPedidoResponse formaPagamento;
    private List<ProdutoPedidoResponse> itensPedido;

    public static class UsuarioPedidoResponse {
        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class RestaurantePedidoResponse {
        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class FormaPagamentoPedidoResponse {
        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }

    public static class ProdutoPedidoResponse {
        private Long id;
        private String nomeProduto;
        private BigDecimal preco;
        private Integer quantidade;
        private BigDecimal valorDesconto;
        private BigDecimal subtotal;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNomeProduto() {
            return nomeProduto;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public BigDecimal getPreco() {
            return preco;
        }

        public void setPreco(BigDecimal preco) {
            this.preco = preco;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

        public BigDecimal getValorDesconto() {
            return valorDesconto;
        }

        public void setValorDesconto(BigDecimal valorDesconto) {
            this.valorDesconto = valorDesconto;
        }

        public BigDecimal getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public OffsetDateTime getDatahoraPedido() {
        return datahoraPedido;
    }

    public void setDatahoraPedido(OffsetDateTime datahoraPedido) {
        this.datahoraPedido = datahoraPedido;
    }

    public OffsetDateTime getDatahoraConfirmacao() {
        return datahoraConfirmacao;
    }

    public void setDatahoraConfirmacao(OffsetDateTime datahoraConfirmacao) {
        this.datahoraConfirmacao = datahoraConfirmacao;
    }

    public OffsetDateTime getDatahoraCancelamento() {
        return datahoraCancelamento;
    }

    public void setDatahoraCancelamento(OffsetDateTime datahoraCancelamento) {
        this.datahoraCancelamento = datahoraCancelamento;
    }

    public OffsetDateTime getDatahoraEntrega() {
        return datahoraEntrega;
    }

    public void setDatahoraEntrega(OffsetDateTime datahoraEntrega) {
        this.datahoraEntrega = datahoraEntrega;
    }

    public UsuarioPedidoResponse getUsuarioPedido() {
        return usuarioPedido;
    }

    public void setUsuarioPedido(UsuarioPedidoResponse usuarioPedido) {
        this.usuarioPedido = usuarioPedido;
    }

    public RestaurantePedidoResponse getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestaurantePedidoResponse restaurante) {
        this.restaurante = restaurante;
    }

    public FormaPagamentoPedidoResponse getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoPedidoResponse formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ProdutoPedidoResponse> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ProdutoPedidoResponse> itensPedido) {
        this.itensPedido = itensPedido;
    }
}
