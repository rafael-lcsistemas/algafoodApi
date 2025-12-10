package com.algaworks.algafoodapi.api.model.mixin;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.model.entity.Endereco;
import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import com.algaworks.algafoodapi.domain.model.entity.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class RestauranteMixin {

//    @JsonIgnore
    private OffsetDateTime datahoraCadastro;

//    @JsonIgnore
    private OffsetDateTime datahoraAlteracao;

    @JsonIgnore
    private Endereco endereco;

    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Cozinha cozinha;

   @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private List<FormaPagamento> formasPagamento = new ArrayList<>();

    @JsonIgnore
    private List<Produto> produtos = new ArrayList<>();
}
