package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail implements Notificador {

    @Override
    public void notificar(Cliente cliente, String msg) {
        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), msg);
    }

}
