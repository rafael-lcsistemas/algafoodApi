package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PRODUTO_NÃO_ENCONTRADO = "Pooduto do código %s não encontrado";

    public ProdutoNaoEncontradaException(UUID id) {
        super(String.format(PRODUTO_NÃO_ENCONTRADO, id));
    }

    public ProdutoNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(PRODUTO_NÃO_ENCONTRADO, id), cause);
    }
}
