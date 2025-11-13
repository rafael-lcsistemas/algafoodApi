package com.algafood.algafoodapi.service.notificador;

import com.algafood.algafoodapi.anotations.TipoNotificador;
import com.algafood.algafoodapi.configuration.NotificadorProperties;
import com.algafood.algafoodapi.enums.NivelUrgencia;
import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TipoNotificador(NivelUrgencia.BAIXO)
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    @Override
    public void notificar(Cliente cliente, String msg) {
        System.out.println("HOST do servidor: " + properties.getHostServidor());
        System.out.println("PORT do servidor: " + properties.getPortServidor());

        System.out.printf("Notificando %s através do e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), msg);
    }

}
