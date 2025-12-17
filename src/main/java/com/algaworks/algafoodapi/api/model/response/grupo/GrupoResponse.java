package com.algaworks.algafoodapi.api.model.response.grupo;

import com.algaworks.algafoodapi.api.model.response.permissao.PermissaoResumeResponse;

import java.util.List;
import java.util.UUID;

public class GrupoResponse {

    private UUID id;
    private Integer codInterno;
    private String nome;
    private Boolean status;
    private List<PermissaoResumeResponse> permissoes;

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

    public List<PermissaoResumeResponse> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoResumeResponse> permissoes) {
        this.permissoes = permissoes;
    }
}
