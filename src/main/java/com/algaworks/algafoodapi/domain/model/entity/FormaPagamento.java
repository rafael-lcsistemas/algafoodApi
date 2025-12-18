package com.algaworks.algafoodapi.domain.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Integer codInterno;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Boolean status;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraAlteracao;

    public FormaPagamento() {
    }

    public FormaPagamento(Integer codInterno, String nome, Boolean status) {
        this.codInterno = codInterno;
        this.nome = nome;
        this.status = status;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean ativo) {
        this.status = ativo;
    }

    public OffsetDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public OffsetDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FormaPagamento that = (FormaPagamento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
