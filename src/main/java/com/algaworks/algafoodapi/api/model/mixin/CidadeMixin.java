package com.algaworks.algafoodapi.api.model.mixin;

import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

public class CidadeMixin {

    @JsonIgnoreProperties(value = {"nome", "uf"}, allowGetters = true)
    private Estado estado;

    @JsonIgnore
    private LocalDateTime datahoraCadastro;

    @JsonIgnore
    private LocalDateTime datahoraAlteracao;
}
