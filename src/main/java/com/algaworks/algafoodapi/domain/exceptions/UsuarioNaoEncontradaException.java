package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
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
