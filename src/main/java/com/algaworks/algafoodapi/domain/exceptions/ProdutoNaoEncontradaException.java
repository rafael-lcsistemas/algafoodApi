package com.algaworks.algafoodapi.domain.exceptions;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PRODUTO_NÃO_ENCONTRADO = "Pooduto do código %d não encontrado";

    public ProdutoNaoEncontradaException(Long id) {
        super(String.format(PRODUTO_NÃO_ENCONTRADO, id));
    }

    public ProdutoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(PRODUTO_NÃO_ENCONTRADO, id), cause);
    }
}
