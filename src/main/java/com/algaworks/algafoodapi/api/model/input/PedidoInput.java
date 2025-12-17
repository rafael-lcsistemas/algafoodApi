package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class PedidoInput {

    @NotNull
    private UUID idusuario;

    @NotNull
    private UUID idrestaurante;

    @NotNull
    private UUID idformapagamento;

    @NotNull
    private List<ItensPedidoResponse> det;

    public static class ItensPedidoResponse {
        @NotNull
        private UUID idproduto;

        @PositiveOrZero
        private BigDecimal quantidade;

        @PositiveOrZero
        private BigDecimal valordesconto;

        private String observacao;

        public UUID getIdproduto() {
            return idproduto;
        }

        public void setIdproduto(UUID idproduto) {
            this.idproduto = idproduto;
        }

        public BigDecimal getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(BigDecimal quantidade) {
            this.quantidade = quantidade;
        }

        public BigDecimal getValordesconto() {
            return valordesconto;
        }

        public void setValordesconto(BigDecimal valordesconto) {
            this.valordesconto = valordesconto;
        }

        public String getObservacao() {
            return observacao;
        }

        public void setObservacao(String observacao) {
            this.observacao = observacao;
        }
    }

    public UUID getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(UUID idusuario) {
        this.idusuario = idusuario;
    }

    public UUID getIdrestaurante() {
        return idrestaurante;
    }

    public void setIdrestaurante(UUID idrestaurante) {
        this.idrestaurante = idrestaurante;
    }

    public UUID getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(UUID idformapagamento) {
        this.idformapagamento = idformapagamento;
    }

    public List<ItensPedidoResponse> getDet() {
        return det;
    }

    public void setDet(List<ItensPedidoResponse> det) {
        this.det = det;
    }
}
