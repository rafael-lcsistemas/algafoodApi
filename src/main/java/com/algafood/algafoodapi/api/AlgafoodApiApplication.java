package com.algafood.algafoodapi.api;

import com.algafood.algafoodapi.model.Cliente;
import com.algafood.algafoodapi.service.ClienteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlgafoodApiApplication {



    public static void main(String[] args) {

        Cliente cliente1 = new Cliente(1, "Rafael");
        Cliente cliente2 = new Cliente(2, "José");

        ClienteService service = new ClienteService();

        service.inativar(cliente1);
        service.ativar(cliente2);

        SpringApplication.run(AlgafoodApiApplication.class, args);

    }

}
