package com.algaworks.algafoodapi.api.model.response;

import java.util.List;

public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Boolean status;
    private List<grupoGenericResponse> grupos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<grupoGenericResponse> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<grupoGenericResponse> grupos) {
        this.grupos = grupos;
    }

    public static class grupoGenericResponse {

        private Long id;
        private String nome;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }
    }
}


