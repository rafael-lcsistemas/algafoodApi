package com.algaworks.algafoodapi.api.model.response;

import com.algaworks.algafoodapi.api.model.response.cidade.CidadeResumeResponse;

public class EnderecoResponse {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private CidadeResumeResponse cidade;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public CidadeResumeResponse getCidade() {
        return cidade;
    }

    public void setCidade(CidadeResumeResponse cidade) {
        this.cidade = cidade;
    }
}
