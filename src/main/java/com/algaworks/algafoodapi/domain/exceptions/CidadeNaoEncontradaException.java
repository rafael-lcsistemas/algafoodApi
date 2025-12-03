package com.algaworks.algafoodapi.domain.exceptions;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String NÃO_ENCONTRADO = "Cidade do código %d não encontrado";

    public CidadeNaoEncontradaException(Long id) {
        super(String.format(NÃO_ENCONTRADO, id));
    }

    public CidadeNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(NÃO_ENCONTRADO, id), cause);
    }
}
