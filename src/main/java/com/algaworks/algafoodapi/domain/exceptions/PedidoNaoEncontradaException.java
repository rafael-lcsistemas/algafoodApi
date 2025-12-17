package com.algaworks.algafoodapi.domain.exceptions;

import java.util.UUID;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PEDIDO_NÃO_ENCONTRADO = "Pedido do código %s não encontrado";

    public PedidoNaoEncontradaException(UUID id) {
        super(String.format(PEDIDO_NÃO_ENCONTRADO, id));
    }

    public PedidoNaoEncontradaException(UUID id, Throwable cause) {
        super(String.format(PEDIDO_NÃO_ENCONTRADO, id), cause);
    }
}
