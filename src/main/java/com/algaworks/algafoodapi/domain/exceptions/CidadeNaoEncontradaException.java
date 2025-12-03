package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradaException(Long id) {
        super(String.format("Cidade do código %d não encontrado", id));
    }

    public CidadeNaoEncontradaException(Long id, Throwable cause) {
        super(String.format("Cidade do código %d não encontrado", id), cause);
    }
}
