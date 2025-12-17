package com.algaworks.algafoodapi.api.model.response.restaurante;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class RestauranteMovResponse {

    private UUID id;
    private String nomeRestaurante;
    private String nomeUsuario;
    private BigDecimal valorMovimento;
    private OffsetDateTime datahoraMovimento;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
