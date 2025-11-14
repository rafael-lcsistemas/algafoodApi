package com.algafood.algafoodapi.domain.jpa.formaPagamento;

import com.algafood.algafoodapi.AlgafoodApiApplication;
import com.algafood.algafoodapi.domain.entity.FormaPagamento;
import com.algafood.algafoodapi.infrastructure.FormaPagamentoService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class Update {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoService formaPagamentoService = context.getBean(FormaPagamentoService.class);

        FormaPagamento pagamento = new FormaPagamento();
        pagamento.setId(1L);
        pagamento.setNome("Boleto");

        pagamento = formaPagamentoService.insertOrUpdate(pagamento);

        System.out.println(pagamento);
    }
}
