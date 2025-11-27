package com.algaworks.algafoodapi.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private BigDecimal taxaFrete;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_cozinha", nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @Embedded
    private RestauranteEndereco endereco;

//    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "id_restaurante"),
            inverseJoinColumns = @JoinColumn(name = "id_forma_pagamento"))
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dataAlteracao;

    public Restaurante() {
    }

    public Restaurante(Long id, String nome, BigDecimal taxaFrete, Cozinha cozinha, List<FormaPagamento> formasPagamento, RestauranteEndereco endereco, LocalDateTime dataCadastro, LocalDateTime dataAlteracao, List<Produto> produtos) {
        this.id = id;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinha = cozinha;
        this.formasPagamento = formasPagamento;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.dataAlteracao = dataAlteracao;
        this.produtos = produtos;
    }

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

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public List<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public RestauranteEndereco getEndereco() {
        return endereco;
    }

    public void setEndereco(RestauranteEndereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Restaurante: " + id + " - " + nome + " / Cozinha: " + cozinha.getNome() +
                " / Forma de pagamento: ";
    }
}
