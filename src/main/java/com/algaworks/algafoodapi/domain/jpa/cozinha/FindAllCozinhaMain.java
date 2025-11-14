package com.algaworks.algafoodapi.domain.jpa.cozinha;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.entity.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAllCozinhaMain {

    public static void main(String[] args) {

        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class).
                web(WebApplicationType.NONE).run(args);

        CozinhaRepository cozinhaRepository = context.getBean(CozinhaRepository.class);

        List<Cozinha> cozinhas = cozinhaRepository.findAll();

        cozinhas.forEach(System.out::println);
    }
}
