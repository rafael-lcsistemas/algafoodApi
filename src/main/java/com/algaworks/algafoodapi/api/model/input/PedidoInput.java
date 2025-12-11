package com.algaworks.algafoodapi.api.model.input;

import com.algaworks.algafoodapi.core.validation.PrecoValidacao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

public class PedidoInput {

    @NotNull
    private Long idusuario;

    @NotNull
    private Long idrestaurante;

    @NotNull
    private Long idformapagamento;

    @NotNull
    private List<ItensPedidoResponse> det;

    public static class ItensPedidoResponse {
        @NotNull
        private Long idproduto;

        @PrecoValidacao
        private BigDecimal preco;

        @PositiveOrZero
        private BigDecimal quantidade;

        @PositiveOrZero
        private BigDecimal valordesconto;

        private String observacao;

        public Long getIdproduto() {
            return idproduto;
        }

        public void setIdproduto(Long idproduto) {
            this.idproduto = idproduto;
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

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public Long getIdrestaurante() {
        return idrestaurante;
    }

    public void setIdrestaurante(Long idrestaurante) {
        this.idrestaurante = idrestaurante;
    }

    public Long getIdformapagamento() {
        return idformapagamento;
    }

    public void setIdformapagamento(Long idformapagamento) {
        this.idformapagamento = idformapagamento;
    }

    public List<ItensPedidoResponse> getDet() {
        return det;
    }

    public void setDet(List<ItensPedidoResponse> det) {
        this.det = det;
    }
}
