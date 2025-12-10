package com.algaworks.algafoodapi.domain.model.entity;

import com.algaworks.algafoodapi.core.validation.Groups;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    @Valid
    @NotNull
    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraCadastro;

    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime datahoraAlteracao;

    public Cidade() {}

    public Cidade(Long id, String nome, Boolean status, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        Cidade cidade = (Cidade) o;
        return Objects.equals(id, cidade.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cidade: " +  id + " - " + nome + " / " + estado;
    }
}
