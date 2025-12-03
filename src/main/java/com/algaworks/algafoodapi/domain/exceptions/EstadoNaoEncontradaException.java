package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String ESTADO_NÃO_ENCONTRADA = "Estado do código %d não encontrado";

    public EstadoNaoEncontradaException(Long id) {
        super(String.format(ESTADO_NÃO_ENCONTRADA, id));
    }

    public EstadoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(ESTADO_NÃO_ENCONTRADA, id), cause);
    }
}
