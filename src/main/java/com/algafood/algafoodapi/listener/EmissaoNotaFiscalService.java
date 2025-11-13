package com.algafood.algafoodapi.listener;

import com.algafood.algafoodapi.service.cliente.ClienteEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void emitirNotaFiscalCliente(ClienteEvent event) {
        if(event.getCliente() == null) {
            return;
        }

        if(event.getCliente().getAtivo()) {
            System.out.println("Emitindo NF-e para o(a) cliente " + event.getCliente().getNome());
        }
    }
}
