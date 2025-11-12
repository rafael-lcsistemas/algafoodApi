package com.algafood.algafoodapi.interfaces;

import com.algafood.algafoodapi.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String msg);
}
