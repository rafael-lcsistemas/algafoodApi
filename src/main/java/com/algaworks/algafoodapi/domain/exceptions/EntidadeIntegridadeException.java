package com.algaworks.algafoodapi.domain.exceptions;

public class EntidadeIntegridadeException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public EntidadeIntegridadeException(String mensagem) {
        super(mensagem);
    }
}
