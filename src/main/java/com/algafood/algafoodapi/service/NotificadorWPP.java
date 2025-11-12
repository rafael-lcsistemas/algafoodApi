package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.anotations.TipoNotificador;
import com.algafood.algafoodapi.enums.NivelUrgencia;
import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.stereotype.Component;

@TipoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorWPP implements Notificador {

    @Override
    public void notificar(Cliente cliente, String msg) {
        System.out.printf("Notificando %s através do WhatsApp %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), msg);
    }

}
