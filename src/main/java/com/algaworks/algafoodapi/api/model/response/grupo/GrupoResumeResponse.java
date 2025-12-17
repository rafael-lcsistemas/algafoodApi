package com.algaworks.algafoodapi.api.model.response.grupo;

import java.util.UUID;

public class GrupoResumeResponse {

    private UUID id;
    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
