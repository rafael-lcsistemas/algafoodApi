package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;

public class EstadoInput {

    @NotBlank
    private String nome;

    @NotBlank
    private String uf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
