package com.algafood.algafoodapi.listener;

import com.algafood.algafoodapi.anotations.TipoNotificador;
import com.algafood.algafoodapi.enums.NivelUrgencia;
import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.service.cliente.ClienteEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteNotificacaoListener(ClienteEvent event) {
        if (event.getCliente() == null) {
            return;
        }

        if (event.getCliente().getAtivo()) {
            notificador.notificar(event.getCliente(), "Seu cadastro no sistema esta ativo!");
        } else {
            notificador.notificar(event.getCliente(), "Seu cadastro no sistema esta inativo!");
        }
    }
}
