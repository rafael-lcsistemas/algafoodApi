package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String CATEGORIA_NÃO_ENCONTRADA = "Categoria do código %s não encontrada";

    public CategoriaNaoEncontradaException(UUID id) {
        super(String.format(CATEGORIA_NÃO_ENCONTRADA, id));
    }

    public CategoriaNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(CATEGORIA_NÃO_ENCONTRADA, id), cause);
    }
}
