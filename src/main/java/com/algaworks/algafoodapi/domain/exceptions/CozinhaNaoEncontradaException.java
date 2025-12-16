package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String COZINHA_NÃO_ENCONTRADA = "Cozinha do código %s não encontrado";

    public CozinhaNaoEncontradaException(UUID id) {
        super(String.format(COZINHA_NÃO_ENCONTRADA, id));
    }

    public CozinhaNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(COZINHA_NÃO_ENCONTRADA, id), cause);
    }
}
