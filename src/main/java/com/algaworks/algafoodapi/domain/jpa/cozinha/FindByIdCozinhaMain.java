package com.algaworks.algafoodapi.domain.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FindByIdCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).
                web(WebApplicationType.NONE).run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);

        Cozinha cozinha = cozinhaRepository.findById(1L);

        System.out.println(cozinha);
    }
}
