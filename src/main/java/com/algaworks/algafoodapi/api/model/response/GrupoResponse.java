package com.algaworks.algafoodapi.api.model.response;

import java.util.List;

public class GrupoResponse {

    private Long id;
    private String nome;
    private Boolean status;
    private List<PermissaoResponse> permissoes;

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

    public List<PermissaoResponse> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<PermissaoResponse> permissoes) {
        this.permissoes = permissoes;
    }
}
