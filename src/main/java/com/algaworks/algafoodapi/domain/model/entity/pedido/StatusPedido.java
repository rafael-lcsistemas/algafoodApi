package com.algaworks.algafoodapi.domain.model.entity.pedido;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO, CONFIRMADO);

    private String descricao;
    private List<StatusPedido> statusPedidosAnteriores;

    StatusPedido(String descricao, StatusPedido... statusPedidosAnteriores) {
        this.descricao = descricao;
        this.statusPedidosAnteriores = Arrays.asList(statusPedidosAnteriores);
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean naoPodeAlterarStatusPara(StatusPedido novoStatusPedido) {
        return !novoStatusPedido.naoPodeAlterarStatusPara(this);
    }
}
