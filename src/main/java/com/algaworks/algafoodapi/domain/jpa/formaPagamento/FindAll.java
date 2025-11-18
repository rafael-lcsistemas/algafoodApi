package com.algaworks.algafoodapi.domain.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.entity.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.repository.FormaPagamentoRepositoryImpl;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAll {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoRepositoryImpl formaPagamentoRepositoryImpl = context.getBean(FormaPagamentoRepositoryImpl.class);

        List<FormaPagamento> pagamentos = formaPagamentoRepositoryImpl.findAll();

        pagamentos.forEach(System.out::println);
    }
}
