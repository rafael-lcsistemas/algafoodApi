package com.algaworks.algafoodapi.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PERMISSAO_NÃO_ENCONTRADA = "Permissão do código %d não encontrado";

    public PermissaoNaoEncontradaException(Long id) {
        super(String.format(PERMISSAO_NÃO_ENCONTRADA, id));
    }

    public PermissaoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(PERMISSAO_NÃO_ENCONTRADA, id), cause);
    }
}
