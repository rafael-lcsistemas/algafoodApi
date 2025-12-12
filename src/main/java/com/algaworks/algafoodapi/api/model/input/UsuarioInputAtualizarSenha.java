package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.NotBlank;

public class UsuarioInputAtualizarSenha {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
    }
}
