package com.algaworks.algafoodapi.domain.model.entity.restaurante;

import com.algaworks.algafoodapi.domain.model.entity.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
public class RestauranteMov {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @PositiveOrZero
    @Column(nullable = false)
    private BigDecimal valorMovimento;

    @Column(length = 500)
    private String observacoes;

    public RestauranteMov() {
    }

    public RestauranteMov(Long id, Restaurante restaurante, Usuario usuario, OffsetDateTime datahoraMovimento,
                          TipoMov tipoMovimento, BigDecimal valorMovimento, String observacoes) {
        this.id = id;
        this.restaurante = restaurante;
        this.usuario = usuario;
        this.datahoraMovimento = datahoraMovimento;
        this.tipoMovimento = tipoMovimento;
        this.valorMovimento = valorMovimento;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

