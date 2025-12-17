package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String GRUPO_NÃO_ENCONTRADO = "Grupo do código %s não encontrado";

    public GrupoNaoEncontradaException(UUID id) {
        super(String.format(GRUPO_NÃO_ENCONTRADO, id));
    }

    public GrupoNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(GRUPO_NÃO_ENCONTRADO, id), cause);
    }
}
