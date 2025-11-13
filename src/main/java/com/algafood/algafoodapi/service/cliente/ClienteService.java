package com.algafood.algafoodapi.service.cliente;

import com.algafood.algafoodapi.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;


@Component
public class ClienteService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        publisher.publishEvent(new ClienteEvent(cliente));
    }

    public void inativar(Cliente cliente) {
        cliente.inativar();

        publisher.publishEvent(new ClienteEvent(cliente));
    }
}
