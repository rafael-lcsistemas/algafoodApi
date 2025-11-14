package com.algaworks.algafoodapi.domain.jpa.formaPagamento;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.entity.FormaPagamento;
import com.algaworks.algafoodapi.infrastructure.FormaPagamentoService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class Insert {

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE).run(args);

        FormaPagamentoService formaPagamentoService = context.getBean(FormaPagamentoService.class);

        FormaPagamento pagamento = new FormaPagamento();
        pagamento.setNome("Boleto");

        pagamento = formaPagamentoService.insertOrUpdate(pagamento);

        System.out.println(pagamento);
    }
}
