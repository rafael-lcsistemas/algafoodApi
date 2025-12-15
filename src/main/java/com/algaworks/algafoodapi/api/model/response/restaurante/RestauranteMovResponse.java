package com.algaworks.algafoodapi.api.model.response.restaurante;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class RestauranteMovResponse {

    private Long id;
    private String nomeRestaurante;
    private String nomeUsuario;
    private BigDecimal valorMovimento;
    private OffsetDateTime datahoraMovimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeRestaurante() {
        return nomeRestaurante;
    }

    public void setNomeRestaurante(String nomeRestaurante) {
        this.nomeRestaurante = nomeRestaurante;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public BigDecimal getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(BigDecimal valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public OffsetDateTime getDatahoraMovimento() {
        return datahoraMovimento;
    }

    public void setDatahoraMovimento(OffsetDateTime datahoraMovimento) {
        this.datahoraMovimento = datahoraMovimento;
    }
}
