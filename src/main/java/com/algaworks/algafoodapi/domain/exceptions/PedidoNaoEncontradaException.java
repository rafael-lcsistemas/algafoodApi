package com.algaworks.algafoodapi.domain.exceptions;

public class PedidoNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public static final String PEDIDO_NÃO_ENCONTRADO = "Pedido do código %d não encontrado";

    public PedidoNaoEncontradaException(Long id) {
        super(String.format(PEDIDO_NÃO_ENCONTRADO, id));
    }

    public PedidoNaoEncontradaException(Long id, Throwable cause) {
        super(String.format(PEDIDO_NÃO_ENCONTRADO, id), cause);
    }
}
