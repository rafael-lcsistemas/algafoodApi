package com.algaworks.algafoodapi.api.model.response.pedido;

import com.algaworks.algafoodapi.domain.model.entity.pedido.StatusPedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class PedidoResumeResponse {

    private UUID id;
    private Integer codInterno;
    private BigDecimal total;
    private BigDecimal taxaFrete;
    private BigDecimal valorDesconto;
    private BigDecimal subtotal;
    private StatusPedido statusPedido;
    private OffsetDateTime datahoraPedido;
    private OffsetDateTime datahoraConfirmacao;
    private OffsetDateTime datahoraCancelamento;
    private OffsetDateTime datahoraEntrega;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getCodInterno() {
        return codInterno;
    }

    public void setCodInterno(Integer codInterno) {
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
}
