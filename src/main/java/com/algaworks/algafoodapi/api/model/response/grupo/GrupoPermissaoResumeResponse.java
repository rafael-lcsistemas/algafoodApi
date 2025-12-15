package com.algaworks.algafoodapi.api.model.response.grupo;

import com.algaworks.algafoodapi.api.model.response.permissao.PermissaoResumeResponse;

import java.util.List;

public class GrupoPermissaoResumeResponse {

    private Long id;
    private String nome;
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

    public List<PermissaoResumeResponse> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoResumeResponse> permissoes) {
        this.permissoes = permissoes;
    }
}
