package com.algaworks.algafoodapi.api.model.response.usuario;

import com.algaworks.algafoodapi.api.model.response.grupo.GrupoResumeResponse;

import java.util.List;
import java.util.UUID;

public class UsuarioResponse {

    private UUID id;
    private Integer codInterno;
    private String nome;
    private String email;
    private Boolean status;
    private List<GrupoResumeResponse> grupos;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<GrupoResumeResponse> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<GrupoResumeResponse> grupos) {
        this.grupos = grupos;
    }
}


