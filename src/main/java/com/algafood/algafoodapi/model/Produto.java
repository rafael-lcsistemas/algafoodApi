package com.algafood.algafoodapi.model;

public class Produto {

    private Integer id;
    private String nome;
    private Double preco;

    public Produto() {

    }

    public Produto(Integer id, Double preco, String nome) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
