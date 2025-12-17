package com.algaworks.algafoodapi.api.model.response;

import com.algaworks.algafoodapi.api.model.response.categoria.CategoriaResumeResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoResponse {

    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean status;
    private CategoriaResumeResponse categoria;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CategoriaResumeResponse getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaResumeResponse categoria) {
        this.categoria = categoria;
    }
}
