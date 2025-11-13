package com.algafood.algafoodapi;

import com.algafood.algafoodapi.model.Cliente;
import com.algafood.algafoodapi.service.cliente.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MeuPrimeiroController {

    private ClienteService clienteService;

    public MeuPrimeiroController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/hello")
    public List<Cliente> hello() {
        Cliente cliente1 = new Cliente(1, "Rafael", "rafael@gmail.com", "(91)99120-0793", true);
        Cliente cliente2 = new Cliente(2, "José", "jose@gmail.com", "(99)99999-9999", false);

        clienteService.inativar(cliente1);
        clienteService.ativar(cliente2);

        return Arrays.asList(cliente1, cliente2);
    }
}
