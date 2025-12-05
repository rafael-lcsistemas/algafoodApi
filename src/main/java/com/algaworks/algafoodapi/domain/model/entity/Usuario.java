package com.algaworks.algafoodapi.domain.model.entity;

import com.algaworks.algafoodapi.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @Email
    @Column(length = 100, unique = true)
    private String email;

    @Column(length = 30)
    private String senha;

    @NotNull
    @Column(nullable = false)
    private Boolean ativo;

    @CreationTimestamp
    private LocalDateTime datahoraCadastro;

    @UpdateTimestamp
    private LocalDateTime datahoraAlteracao;

    @Valid
    @ConvertGroup(from = Default.class, to = Groups.GrupoId.class)
    @NotNull
    @ManyToMany
    @JoinTable(name = "usuario_grupo",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_grupo"))
    private List<Grupo> grupos = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, Boolean ativo, List<Grupo> grupos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ativo = ativo;
        this.grupos = grupos;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDatahoraCadastro() {
        return datahoraCadastro;
    }

    public LocalDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario: " + id + " - " + nome + " / Grupos: " + grupos;
    }
}
