package com.algafood.algafoodapi;

import com.algafood.algafoodapi.model.Cliente;
import com.algafood.algafoodapi.service.ClienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        Cliente cliente1 = new Cliente(1, "Rafael", "rafael@gmail.com");
        Cliente cliente2 = new Cliente(2, "José", "jose@gmail.com");

        clienteService.inativar(cliente1);
        clienteService.ativar(cliente2);

        return Arrays.asList(cliente1, cliente2);
    }
}
