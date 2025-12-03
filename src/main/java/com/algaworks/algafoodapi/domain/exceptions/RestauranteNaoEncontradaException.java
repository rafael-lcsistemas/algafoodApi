package com.algaworks.algafoodapi.domain.exceptions;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String RESTAURANTE_NÃO_ENCONTRADO = "Restaurante do código %d não encontrado";

    public RestauranteNaoEncontradaException(Long id) {
        super(String.format(RESTAURANTE_NÃO_ENCONTRADO, id));
    }

    public RestauranteNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(RESTAURANTE_NÃO_ENCONTRADO, id), cause);
    }
}
