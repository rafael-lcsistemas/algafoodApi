package com.algaworks.algafoodapi.domain.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @CreationTimestamp
    private LocalDateTime datahoraCadastro;

    @CreationTimestamp
    private LocalDateTime datahoraAlteracao;

    public Pais() {
    }

    public Pais(Long id, String nome, LocalDateTime datahoraCadastro, LocalDateTime datahoraAlteracao) {
        this.id = id;
        this.nome = nome;
        this.datahoraCadastro = datahoraCadastro;
        this.datahoraAlteracao = datahoraAlteracao;
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

    public LocalDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public void setDatahoraCadastro(LocalDateTime datahoraCadastro) {
        this.datahoraCadastro = datahoraCadastro;
    }

    public LocalDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    public void setDatahoraAlteracao(LocalDateTime datahoraAlteracao) {
        this.datahoraAlteracao = datahoraAlteracao;
    }

    @Override
    public String toString() {
        return "PAIS: " + id + " - " + nome;
    }
}
