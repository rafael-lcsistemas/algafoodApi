package com.algaworks.algafoodapi.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "id_cozinha", nullable = false)
    private Cozinha cozinha;

    @ManyToOne
    @JoinColumn(name = "id_formaPagamento", nullable = false)
    private FormaPagamento formaPagamento;

    public Restaurante() {
    }

    public Restaurante(Long id, String nome, BigDecimal taxaFrete, Cozinha cozinha, FormaPagamento formaPagamento) {
        this.id = id;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.cozinha = cozinha;
        this.formaPagamento = formaPagamento;
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

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
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
                " / Forma de pagamento: " + formaPagamento.getNome();
    }
}
