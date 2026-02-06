package com.algaworks.algafoodapi.domain.model.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ProdutoFoto {

    @Id
    @Column(name = "produto_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    @NotBlank
    @Column(length = 150)
    private String nomeArquivo;

    @Column(length = 150)
    private String descricao;

    @NotBlank
    private String contentType;

    @NotNull
    private Long tamanho;

    @UpdateTimestamp
    private OffsetDateTime datahoraAlteracao;

    public ProdutoFoto() {
    }

    public ProdutoFoto(UUID id, Produto produto, String nomeArquivo, String descricao, String contentType, Long tamanho, OffsetDateTime datahoraAlteracao) {
        this.id = id;
        this.produto = produto;
        this.nomeArquivo = nomeArquivo;
        this.descricao = descricao;
        this.contentType = contentType;
        this.tamanho = tamanho;
        this.datahoraAlteracao = datahoraAlteracao;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public OffsetDateTime getDatahoraAlteracao() {
        return datahoraAlteracao;
    }

    public void setDatahoraAlteracao(OffsetDateTime datahoraAlteracao) {
        this.datahoraAlteracao = datahoraAlteracao;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        ProdutoFoto that = (ProdutoFoto) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
