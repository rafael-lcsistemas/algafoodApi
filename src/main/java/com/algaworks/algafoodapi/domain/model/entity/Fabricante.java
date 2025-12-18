package com.algaworks.algafoodapi.domain.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Fabricante {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(nullable = false, unique = true, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private Integer codInterno;

    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    private Boolean status;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraCadastro;

    @UpdateTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraAtualizacao;

    public Fabricante() {}

    public Fabricante(Integer codInterno, String nome, Boolean status) {
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

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public OffsetDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public OffsetDateTime getDatahoraAtualizacao() {
        return datahoraAtualizacao;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Fabricante that = (Fabricante) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
