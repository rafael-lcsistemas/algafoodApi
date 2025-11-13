package com.algafood.algafoodapi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

    private String hostServidor;
    private String portServidor;

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public String getPortServidor() {
        return portServidor;
    }

    public void setPortServidor(String portServidor) {
        this.portServidor = portServidor;
    }
}
