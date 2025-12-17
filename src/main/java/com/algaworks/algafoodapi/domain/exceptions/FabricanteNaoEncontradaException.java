package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class FabricanteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String FABRICANTE_NÃO_ENCONTRADA = "Fabricante do código %s não encontrada";

    public FabricanteNaoEncontradaException(UUID id) {
        super(String.format(FABRICANTE_NÃO_ENCONTRADA, id));
    }

    public FabricanteNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(FABRICANTE_NÃO_ENCONTRADA, id), cause);
    }
}
