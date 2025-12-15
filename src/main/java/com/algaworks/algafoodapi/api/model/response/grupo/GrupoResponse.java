package com.algaworks.algafoodapi.api.model.response.grupo;

import com.algaworks.algafoodapi.api.model.response.permissao.PermissaoResumeResponse;

import java.util.List;

public class GrupoResponse {

    private Long id;
    private String nome;
    private Boolean status;
    private List<PermissaoResumeResponse> permissoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
