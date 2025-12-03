package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String COZINHA_NÃO_ENCONTRADA = "Cozinha do código %d não encontrado";

    public CozinhaNaoEncontradaException(Long id) {
        super(String.format(COZINHA_NÃO_ENCONTRADA, id));
    }

    public CozinhaNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(COZINHA_NÃO_ENCONTRADA, id), cause);
    }
}
