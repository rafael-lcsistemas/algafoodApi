package com.algaworks.algafoodapi.api.model.mixin;

import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;

public class ProdutoMixin {

    @JsonIgnoreProperties(value = {"nome", "taxaFrete", "ativo"}, allowGetters = true)
    private Restaurante restaurante;

    @JsonIgnore
    private OffsetDateTime dataCadastro;

    @JsonIgnore
    private OffsetDateTime dataAlteracao;
}
