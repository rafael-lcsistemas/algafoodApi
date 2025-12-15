package com.algaworks.algafoodapi.api.model.response.restaurante;

import com.algaworks.algafoodapi.api.model.response.EnderecoResponse;
import com.algaworks.algafoodapi.api.model.response.cozinha.CozinhaResumeResponse;

import java.math.BigDecimal;

public class RestauranteResponse {

    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private Boolean status;
    private Boolean aberto;
    private CozinhaResumeResponse cozinha;
    private EnderecoResponse endereco;

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

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public CozinhaResumeResponse getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaResumeResponse cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoResponse getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoResponse endereco) {
        this.endereco = endereco;
    }
}
