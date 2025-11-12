package com.algafood.algafoodapi.model;

public class Cliente {

    private Integer id;
    private String nome;
    private String email;
    private Boolean ativo;

//    public Cliente() {
//        this.ativo = true;
//    }

    public Cliente(Integer id, String nome, String email) {
        this.ativo = true;
        this.nome = nome;
        this.id = id;
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
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

    public void ativar() {
        this.ativo = true;
    }

    public void inativar() {
        this.ativo = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
