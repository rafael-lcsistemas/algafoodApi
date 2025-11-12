package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;

public class NotificadorEmail implements Notificador {

    private Boolean caixaAlta;
    private String servidorSMTP;

    public NotificadorEmail(String servidorSMTP) {
        this.servidorSMTP = servidorSMTP;
    }

    @Override
    public void notificar(Cliente cliente, String msg) {
        if(this.caixaAlta) {
            msg = msg.toUpperCase();
        }

        System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s\n",
                cliente.getNome(), cliente.getEmail(), this.servidorSMTP, msg);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
