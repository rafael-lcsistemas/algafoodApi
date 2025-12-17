package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PERMISSAO_NÃO_ENCONTRADA = "Permissão do código %s não encontrado";

    public PermissaoNaoEncontradaException(UUID id) {
        super(String.format(PERMISSAO_NÃO_ENCONTRADA, id));
    }

    public PermissaoNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(PERMISSAO_NÃO_ENCONTRADA, id), cause);
    }
}
