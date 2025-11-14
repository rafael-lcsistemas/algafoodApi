package com.algafood.algafoodapi.domain.jpa.formaPagamento;

import com.algafood.algafoodapi.AlgafoodApiApplication;
import com.algafood.algafoodapi.domain.entity.FormaPagamento;
import com.algafood.algafoodapi.infrastructure.FormaPagamentoService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class FindAll {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoService formaPagamentoService = context.getBean(FormaPagamentoService.class);

        List<FormaPagamento> pagamentos = formaPagamentoService.findAll();

        pagamentos.forEach(System.out::println);
    }
}
