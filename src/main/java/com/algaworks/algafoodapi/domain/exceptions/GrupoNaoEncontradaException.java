package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GrupoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String GRUPO_NÃO_ENCONTRADO = "Grupo do código %d não encontrado";

    public GrupoNaoEncontradaException(Long id) {
        super(String.format(GRUPO_NÃO_ENCONTRADO, id));
    }

    public GrupoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(GRUPO_NÃO_ENCONTRADO, id), cause);
    }
}
