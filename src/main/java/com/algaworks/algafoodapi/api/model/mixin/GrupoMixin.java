package com.algaworks.algafoodapi.api.model.mixin;

import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GrupoMixin {

    @JsonIgnore
    private LocalDateTime datahoraCadastro;

    @JsonIgnore
    private LocalDateTime datahoraAlteracao;

    @JsonIgnoreProperties(value = {"nome", "descricao", "ativo"}, allowGetters = true)
    private List<Permissao> permissoes = new ArrayList<>();
}
