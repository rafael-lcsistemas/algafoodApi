package com.algaworks.algafoodapi.api.model.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class UsuarioInputSemSenha {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Boolean status;

    @NotNull
    private List<Long> idsGrupos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Long> getIdsGrupos() {
        return idsGrupos;
    }

    public void setIdsGrupos(List<Long> idsGrupos) {
        this.idsGrupos = idsGrupos;
    }
}


