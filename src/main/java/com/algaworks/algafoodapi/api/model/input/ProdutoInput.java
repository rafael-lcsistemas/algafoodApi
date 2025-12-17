package com.algaworks.algafoodapi.api.model.input;

import com.algaworks.algafoodapi.core.validation.PrecoValidacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoInput {

    @NotBlank
    private String nome;

    @NotNull
    private String descricao;

    @PrecoValidacao
    private BigDecimal preco;

    @NotNull
    private Boolean status;

    @NotNull
    private UUID idrestaurante;

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

    public UUID getIdrestaurante() {
        return idrestaurante;
    }

    public void setIdrestaurante(UUID idrestaurante) {
        this.idrestaurante = idrestaurante;
    }
}
