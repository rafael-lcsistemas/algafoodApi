package com.algaworks.algafoodapi.domain.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class VendaDiariaFilter {

    private UUID restauranteId;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataPedidoInicio;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private OffsetDateTime dataPedidoFim;

    public UUID getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(UUID restauranteId) {
        this.restauranteId = restauranteId;
    }

    public OffsetDateTime getDataPedidoInicio() {
        return dataPedidoInicio;
    }

    public void setDataPedidoInicio(OffsetDateTime dataPedidoInicio) {
        this.dataPedidoInicio = dataPedidoInicio;
    }

    public OffsetDateTime getDataPedidoFim() {
        return dataPedidoFim;
    }

    public void setDataPedidoFim(OffsetDateTime dataPedidoFim) {
        this.dataPedidoFim = dataPedidoFim;
    }
}
