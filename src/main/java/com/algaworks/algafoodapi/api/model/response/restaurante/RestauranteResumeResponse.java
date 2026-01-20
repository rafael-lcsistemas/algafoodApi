package com.algaworks.algafoodapi.api.model.response.restaurante;

import java.util.UUID;

public class RestauranteResumeResponse {

    private UUID id;
    private Long codInterno;
    private String nome;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
