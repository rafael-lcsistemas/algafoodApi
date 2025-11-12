package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ClienteService {

    private Notificador notificador;

    public ClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema foi ativado!");
    }

    public void inativar(Cliente cliente) {
        cliente.inativar();
        notificador.notificar(cliente, "Seu cadastro no sistema foi inativado!");
    }
}
