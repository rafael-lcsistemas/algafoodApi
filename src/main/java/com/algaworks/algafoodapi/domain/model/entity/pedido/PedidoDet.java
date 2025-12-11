package com.algaworks.algafoodapi.domain.model.entity.pedido;

import com.algaworks.algafoodapi.domain.model.entity.Produto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pedidodet")
public class PedidoDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public PedidoDet(Long id, Pedido pedido, Produto produto, BigDecimal preco, BigDecimal quantidade, BigDecimal total, BigDecimal valorDesconto, BigDecimal subtotal, String observacao) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.total = total;
        this.valorDesconto = valorDesconto;
        this.subtotal = subtotal;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoDet pedidoDet = (PedidoDet) o;
        return Objects.equals(id, pedidoDet.id) && Objects.equals(pedido, pedidoDet.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedido);
    }

    @Override
    public String toString() {
        return "PedidoDet: " + id + " - " + pedido;
    }
}
