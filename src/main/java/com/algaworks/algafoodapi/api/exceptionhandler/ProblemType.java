package com.algaworks.algafoodapi.api.exceptionhandler;

public enum ProblemType {

    REQUISICAO_INVALIDA("Requisição inválida", "/requisicao-invalida"),
    RECURSO_NAO_ENCONTRADO("Recurso não encontrado", "/recurso-nao-encontrado"),
    ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
    DADOS_INVALIDOS("Dados inválidos", "/dados-invalidos"),
    ERRO_DE_SISTEMA("Erro de sistema", "/erro-de-sistema"),
    PARAMENTRO_INVALIDO("Parâmentro invalido", "/parametro-invalido");

    private String title;
    private String uri;

    ProblemType(String title, String path) {
        this.title = title;
        this.uri = "https://algafood.com.br" + path;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
