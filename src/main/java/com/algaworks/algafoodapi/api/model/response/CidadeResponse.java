package com.algaworks.algafoodapi.api.model.response;

public class CidadeResponse {

    private Long id;
    private String nome;
    private Boolean status;
    private EstadoResponse estado;

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

    public EstadoResponse getEstado() {
        return estado;
    }

    public void setEstado(EstadoResponse estado) {
        this.estado = estado;
    }
}
