package com.algafood.algafoodapi.service;

import com.algafood.algafoodapi.model.Cliente;

public class ClienteService {

    public void ativar(Cliente cliente) {
        cliente.ativar();
        System.out.printf("Cliente: %s ativado com sucesso! \n", cliente.getId() + " - " + cliente.getNome());
    }

    public void inativar(Cliente cliente) {
        cliente.inativar();
        System.out.printf("Cliente: %s inativado com sucesso! \n", cliente.getId() + " - " + cliente.getNome());
    }
}
