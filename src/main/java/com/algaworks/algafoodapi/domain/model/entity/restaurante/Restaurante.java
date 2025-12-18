package com.algaworks.algafoodapi.domain.model.entity.restaurante;

import com.algaworks.algafoodapi.domain.model.entity.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Integer codInterno;

    @ManyToOne
    @JoinColumn(name = "id_cozinha", nullable = false)
    private Cozinha cozinha;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = false)
    private Boolean aberto = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraAlteracao;

    @Embedded
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "id_restaurante"),
            inverseJoinColumns = @JoinColumn(name = "id_forma_pagamento"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<RestauranteMov> movimentos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
            joinColumns = @JoinColumn(name = "id_restaurante"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios = new HashSet<>();

    public Restaurante() {
    }

    public Restaurante(Integer codInterno, String nome, BigDecimal taxaFrete, Boolean status, Boolean aberto,
                       Cozinha cozinha, Set<FormaPagamento> formasPagamento, Endereco endereco, List<Produto> produtos,
                       List<RestauranteMov> movimentos, Set<Usuario> usuarios) {
        this.codInterno = codInterno;
        this.nome = nome;
        this.taxaFrete = taxaFrete;
        this.status = status;
        this.aberto = aberto;
        this.cozinha = cozinha;
        this.formasPagamento = formasPagamento;
        this.endereco = endereco;
        this.produtos = produtos;
        this.movimentos = movimentos;
        this.usuarios = usuarios;
    }

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

    public OffsetDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public OffsetDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public Set<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(Set<FormaPagamento> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<RestauranteMov> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<RestauranteMov> movimentos) {
        this.movimentos = movimentos;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public boolean associarFormaPagamento(FormaPagamento formaPagamento) {
        return formasPagamento.add(formaPagamento);
    }

    public boolean desassociarFormaPagamento(FormaPagamento formaPagamento) {
        return formasPagamento.remove(formaPagamento);
    }

    public void abrirRestaurante() {
        this.aberto = Boolean.TRUE;
    }

    public void fecharRestaurante() {
        this.aberto = Boolean.FALSE;
    }

    public void adicionarMovimento(RestauranteMov movimento) {
        movimento.setRestaurante(this);
        this.movimentos.add(movimento);
    }

    public boolean associarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    public boolean desassociarUsuario(Usuario usuario) {
        return usuarios.remove(usuario);
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
}
