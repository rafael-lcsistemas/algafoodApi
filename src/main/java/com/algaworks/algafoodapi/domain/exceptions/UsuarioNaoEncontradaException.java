package com.algaworks.algafoodapi.domain.exceptions;

public class UsuarioNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String USUARIO_NÃO_ENCONTRADO = "Usuário do código %d não encontrado";

    public UsuarioNaoEncontradaException(Long id) {
        super(String.format(USUARIO_NÃO_ENCONTRADO, id));
    }

    public UsuarioNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(USUARIO_NÃO_ENCONTRADO, id), cause);
    }
}
