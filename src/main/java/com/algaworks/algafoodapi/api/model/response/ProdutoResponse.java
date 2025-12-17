package com.algaworks.algafoodapi.api.model.response;

import com.algaworks.algafoodapi.api.model.response.categoria.CategoriaResumeResponse;
import com.algaworks.algafoodapi.api.model.response.fabricante.FabricanteResumeResponse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoResponse {

    private UUID id;
    private Integer codInterno;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Boolean status;
    private CategoriaResumeResponse categoria;
    private FabricanteResumeResponse fabricante;

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

    public FabricanteResumeResponse getFabricante() {
        return fabricante;
    }

    public void setFabricante(FabricanteResumeResponse fabricante) {
        this.fabricante = fabricante;
    }
}
