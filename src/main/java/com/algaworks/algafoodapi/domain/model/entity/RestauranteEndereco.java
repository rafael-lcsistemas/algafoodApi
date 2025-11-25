package com.algaworks.algafoodapi.domain.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class RestauranteEndereco {

    @Column(name = "endereco_cep")
    private String cep;

    @Column(name = "endereco_logradouro")
    private String logradouro;

    @Column(name = "endereco_numero")
    private String numero;

    @Column(name = "endereco_complemento")
    private String complemento;

    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne
    @JoinColumn(name = "endereco_id_cidade")
    private Cidade cidade;

    public RestauranteEndereco() {}

    public RestauranteEndereco(String cep, String logradouro, String numero, String complemento, String bairro, Cidade cidade) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RestauranteEndereco that = (RestauranteEndereco) o;
        return Objects.equals(cep, that.cep) && Objects.equals(logradouro, that.logradouro) && Objects.equals(numero, that.numero) && Objects.equals(complemento, that.complemento) && Objects.equals(bairro, that.bairro) && Objects.equals(cidade, that.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, numero, complemento, bairro, cidade);
    }

    @Override
    public String toString() {
        return "Endereço: " + logradouro + ", " + numero + " - " + complemento + " - " + bairro + " - " + cidade;
    }
}
