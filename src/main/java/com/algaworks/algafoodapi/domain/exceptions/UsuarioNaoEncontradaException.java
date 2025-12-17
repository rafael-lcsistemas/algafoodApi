package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String USUARIO_NÃO_ENCONTRADO = "Usuário do código %s não encontrado";

    public UsuarioNaoEncontradaException(UUID id) {
        super(String.format(USUARIO_NÃO_ENCONTRADO, id));
    }

    public UsuarioNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(USUARIO_NÃO_ENCONTRADO, id), cause);
    }
}
