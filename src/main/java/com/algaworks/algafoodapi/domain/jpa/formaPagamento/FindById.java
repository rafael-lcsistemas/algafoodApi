package com.algaworks.algafoodapi.domain.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.entity.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.FormaPagamentoService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class FindById {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoService formaPagamentoService = context.getBean(FormaPagamentoService.class);

        FormaPagamento pagamento = formaPagamentoService.findById(1L);

        System.out.println(pagamento);
    }
}
