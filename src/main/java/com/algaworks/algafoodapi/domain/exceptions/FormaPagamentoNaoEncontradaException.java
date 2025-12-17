package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PAGAMENTO_NÃO_ENCONTRADO = "Forma de pagamento do código %s não encontrado";

    public FormaPagamentoNaoEncontradaException(UUID id) {
        super(String.format(PAGAMENTO_NÃO_ENCONTRADO, id));
    }

    public FormaPagamentoNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(PAGAMENTO_NÃO_ENCONTRADO, id), cause);
    }
}
