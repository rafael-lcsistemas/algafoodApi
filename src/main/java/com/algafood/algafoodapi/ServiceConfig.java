package com.algafood.algafoodapi;

import com.algafood.algafoodapi.interfaces.Notificador;
import com.algafood.algafoodapi.service.ClienteService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public ClienteService clienteService(Notificador notificador) {
        return new ClienteService(notificador);
    }
}
