package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class GrupoInput {

    @NotBlank
    private String nome;

    @NotNull
    private Boolean status;

    @NotNull
    private List<UUID> idsPermissoes;

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

    public List<UUID> getIdsPermissoes() {
        return idsPermissoes;
    }

    public void setIdsPermissoes(List<UUID> idsPermissoes) {
        this.idsPermissoes = idsPermissoes;
    }
}
