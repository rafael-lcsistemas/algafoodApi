package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.anotations.TipoNotificador;
import com.algafood.algafoodapi.enums.NivelUrgencia;
import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteService {

    @TipoNotificador(NivelUrgencia.URGENTE)
    @Autowired
    private Notificador notificador;

//    @Autowired
//    public ClienteService(Notificador notificador) {
//        this.notificador = notificador;
//    }

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro no sistema foi ativado!");
    }

    public void inativar(Cliente cliente) {
        cliente.inativar();
        notificador.notificar(cliente, "Seu cadastro no sistema foi inativado!");
    }
}
