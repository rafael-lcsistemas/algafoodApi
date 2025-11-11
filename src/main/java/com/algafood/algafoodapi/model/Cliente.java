package com.algafood.algafoodapi.model;

public class Cliente {

    private Integer id;
    private String nome;
    private Boolean ativo;

    public Cliente() {
        this.ativo = true;
    }

    public Cliente(Boolean ativo, String nome, Integer id) {
        this.ativo = ativo;
        this.nome = nome;
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
