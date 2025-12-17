package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class RestauranteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String RESTAURANTE_NÃO_ENCONTRADO = "Restaurante do código %s não encontrado";

    public RestauranteNaoEncontradaException(UUID id) {
        super(String.format(RESTAURANTE_NÃO_ENCONTRADO, id));
    }

    public RestauranteNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(RESTAURANTE_NÃO_ENCONTRADO, id), cause);
    }
}
