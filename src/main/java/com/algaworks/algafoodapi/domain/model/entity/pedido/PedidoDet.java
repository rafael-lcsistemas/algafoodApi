package com.algaworks.algafoodapi.domain.model.entity.pedido;

import com.algaworks.algafoodapi.domain.model.entity.Produto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "pedidodet")
public class PedidoDet {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false, updatable = false)
    private Produto produto;

    @Column(nullable = false)
    private BigDecimal preco;

    @Column(nullable = false)
    private BigDecimal quantidade;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private BigDecimal valorDesconto;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @Column(length = 500)
    private String observacao;

    public PedidoDet() {
    }

    public PedidoDet(Pedido pedido, Produto produto, BigDecimal preco, BigDecimal quantidade, BigDecimal total, BigDecimal valorDesconto, BigDecimal subtotal, String observacao) {
        this.pedido = pedido;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.total = total;
        this.valorDesconto = valorDesconto;
        this.subtotal = subtotal;
        this.observacao = observacao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
