package com.algaworks.algafoodapi.core.squiggly;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SquigglyConfiguration {

    /*
    * ** NO PARAMETRO fields COLOQUE DESSA MANEIRA PARA TRAZER OS DADOS NO RESPONSE **
    *
    *
    * Para pegar um objeto aninhado faça: cliente                          ---> Vai trazer o objeto anhinhado completo
    * Para pegar um objeto aninhado faça: cliente[id,nome,email]           ---> Vai trazer só esses dados do objeto aninhado
    * Para pegar um objeto aninhado menos um atributo faça: cliente[-id]   ---> Vai todos os campos do objeto MENOS o ID
    * Para pegar alguns campos começando com uma palavra faça: sub*        ---> vai trazer todos os atributos que tem subtotal
    * */

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> filterRegistrationBean(ObjectMapper objectMapper) {
        Squiggly.init(objectMapper, new RequestSquigglyContextProvider()); // Nome paramentro padrão: fields

//        var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*"); // Limita somente para esses dois OBJETOS

        var filterRegistrationBean = new FilterRegistrationBean<SquigglyRequestFilter>();
        filterRegistrationBean.setFilter(new SquigglyRequestFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setUrlPatterns(urlPatterns);

        return filterRegistrationBean;
    }
}
