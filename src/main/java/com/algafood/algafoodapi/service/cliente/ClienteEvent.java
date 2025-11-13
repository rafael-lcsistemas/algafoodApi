package com.algafood.algafoodapi.service.cliente;

import com.algafood.algafoodapi.model.Cliente;

public class ClienteEvent {

    private Cliente cliente;

    public ClienteEvent(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
