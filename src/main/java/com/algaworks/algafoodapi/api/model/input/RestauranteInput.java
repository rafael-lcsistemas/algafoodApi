package com.algaworks.algafoodapi.api.model.input;

import com.algaworks.algafoodapi.core.validation.ValorZeroIncluiDescricao;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.UUID;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete grátis")
public class RestauranteInput {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @NotNull
    private Boolean status;

    @NotNull
    private UUID idcozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;

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

    public UUID getIdcozinha() {
        return idcozinha;
    }

    public void setIdcozinha(UUID idcozinha) {
        this.idcozinha = idcozinha;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }
}
