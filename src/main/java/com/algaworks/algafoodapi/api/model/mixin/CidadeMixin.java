package com.algaworks.algafoodapi.api.model.mixin;

import com.algaworks.algafoodapi.domain.model.entity.Estado;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

public class CidadeMixin {

    @JsonIgnoreProperties(value = {"nome", "uf"}, allowGetters = true)
    private Estado estado;

    @JsonIgnore
    private OffsetDateTime datahoraCadastro;

    @JsonIgnore
    private OffsetDateTime datahoraAlteracao;
}
