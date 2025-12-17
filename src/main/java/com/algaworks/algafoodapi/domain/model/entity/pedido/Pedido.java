package com.algaworks.algafoodapi.domain.model.entity.pedido;

import com.algaworks.algafoodapi.domain.model.entity.Endereco;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import com.algaworks.algafoodapi.domain.model.entity.restaurante.Restaurante;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    private Long codInterno;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorDesconto;

    @Column(nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_formaPagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusPedido statusPedido = StatusPedido.CRIADO;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime datahoraPedido;

    private OffsetDateTime datahoraConfirmacao;

    private OffsetDateTime datahoraCancelamento;

    private OffsetDateTime datahoraEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoDet> itensPedido = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(UUID id, Long codInterno, BigDecimal total, BigDecimal taxaFrete, BigDecimal valorDesconto,
                  BigDecimal subtotal, Usuario usuario, Restaurante restaurante, FormaPagamento formaPagamento,
                  Endereco enderecoEntrega, StatusPedido statusPedido, List<PedidoDet> itensPedido) {
        this.id = id;
        this.codInterno = codInterno;
        this.total = total;
        this.taxaFrete = taxaFrete;
        this.valorDesconto = valorDesconto;
        this.subtotal = subtotal;
        this.usuario = usuario;
        this.restaurante = restaurante;
        this.formaPagamento = formaPagamento;
        this.enderecoEntrega = enderecoEntrega;
        this.statusPedido = statusPedido;
        this.itensPedido = itensPedido;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(Long codInterno) {
        this.codInterno = codInterno;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
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

    public List<PedidoDet> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<PedidoDet> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public void addItemPedido(PedidoDet item) {
        item.setPedido(this);
        this.itensPedido.add(item);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Pedido pedido = (Pedido) object;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

