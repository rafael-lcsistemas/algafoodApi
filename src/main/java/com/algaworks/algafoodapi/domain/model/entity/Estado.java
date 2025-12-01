package com.algaworks.algafoodapi.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 5)
    private String uf;

    @Column(nullable = false, length = 100)
    private String nome;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime datahoraCadastro;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime datahoraAlteracao;

    public Estado() {
    }

    public Estado(Long id, String uf, String nome) {
        this.id = id;
        this.uf = uf;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public LocalDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estado estado = (Estado) o;
        return Objects.equals(id, estado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estado: " + id + " - " + nome + " / " + uf;
    }
}
