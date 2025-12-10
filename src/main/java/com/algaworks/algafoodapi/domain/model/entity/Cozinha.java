package com.algaworks.algafoodapi.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")
    private List<Restaurante> restaurantes = new ArrayList<>();

    public Cozinha() {
    }

    public Cozinha(Long id, String nome, Boolean status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
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

    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cozinha cozinha = (Cozinha) o;
        return Objects.equals(id, cozinha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cozinha: " + id + " - " + nome;
    }
}
