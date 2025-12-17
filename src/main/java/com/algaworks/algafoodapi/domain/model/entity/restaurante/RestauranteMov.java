package com.algaworks.algafoodapi.domain.model.entity.restaurante;

import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
public class RestauranteMov {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(nullable = false, unique = true)
    private Integer codInterno;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime datahoraMovimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TipoMov tipoMovimento;

    @Column(nullable = false)
    private BigDecimal valorMovimento;

    @Column(length = 500)
    private String observacoes;

    public RestauranteMov() {
    }

    public RestauranteMov(Integer codInterno, Restaurante restaurante, Usuario usuario, OffsetDateTime datahoraMovimento,
                          TipoMov tipoMovimento, BigDecimal valorMovimento, String observacoes) {
        this.codInterno = codInterno;
        this.restaurante = restaurante;
        this.usuario = usuario;
        this.datahoraMovimento = datahoraMovimento;
        this.tipoMovimento = tipoMovimento;
        this.valorMovimento = valorMovimento;
        this.observacoes = observacoes;
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

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public OffsetDateTime getDatahoraMovimento() {
        return datahoraMovimento;
    }

    public void setDatahoraMovimento(OffsetDateTime datahoraMovimento) {
        this.datahoraMovimento = datahoraMovimento;
    }

    public TipoMov getTipoMovimento() {
        return tipoMovimento;
    }

    public void setTipoMovimento(TipoMov tipoMovimento) {
        this.tipoMovimento = tipoMovimento;
    }

    public BigDecimal getValorMovimento() {
        return valorMovimento;
    }

    public void setValorMovimento(BigDecimal valorMovimento) {
        this.valorMovimento = valorMovimento;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}

