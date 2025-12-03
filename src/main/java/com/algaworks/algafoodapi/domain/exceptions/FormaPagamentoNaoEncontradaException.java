package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PAGAMENTO_NÃO_ENCONTRADO = "Forma de pagamento do código %d não encontrado";

    public FormaPagamentoNaoEncontradaException(Long id) {
        super(String.format(PAGAMENTO_NÃO_ENCONTRADO, id));
    }

    public FormaPagamentoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(PAGAMENTO_NÃO_ENCONTRADO, id), cause);
    }
}
