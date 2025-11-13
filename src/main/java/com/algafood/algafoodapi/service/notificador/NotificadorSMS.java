package com.algafood.algafoodapi.service.notificador;

import com.algafood.algafoodapi.anotations.TipoNotificador;
import com.algafood.algafoodapi.enums.NivelUrgencia;
import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.model.Cliente;
import org.springframework.stereotype.Component;

@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String msg) {
        System.out.printf("Notificando %s através do telefone %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), msg);
    }

}
