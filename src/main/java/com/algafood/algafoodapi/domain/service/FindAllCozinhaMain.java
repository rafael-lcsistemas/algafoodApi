package com.algafood.algafoodapi.domain.service;

import com.algafood.algafoodapi.AlgafoodApiApplication;
import com.algafood.algafoodapi.domain.entity.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).
                web(WebApplicationType.NONE).run(args);

        CozinhaService cozinhaService = context.getBean(CozinhaService.class);

        List<Cozinha> cozinhas = cozinhaService.findAll();

        cozinhas.forEach(cozinha -> {
            System.out.println(cozinha.getNome());
        });
    }
}
