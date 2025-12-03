package com.algaworks.algafoodapi.domain.exceptions;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String ESTADO_NÃO_ENCONTRADA = "Estado do código %d não encontrado";

    public EstadoNaoEncontradaException(Long id) {
        super(String.format(ESTADO_NÃO_ENCONTRADA, id));
    }

    public EstadoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(ESTADO_NÃO_ENCONTRADA, id), cause);
    }
}
