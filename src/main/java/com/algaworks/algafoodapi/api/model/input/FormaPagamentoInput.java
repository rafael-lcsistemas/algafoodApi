package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FormaPagamentoInput {

    @NotBlank
    private String nome;

    @NotNull
    private Boolean status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
