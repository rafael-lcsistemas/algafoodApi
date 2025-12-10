package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.model.response.CozinhaResponse;
import com.algaworks.algafoodapi.api.model.response.RestauranteResponse;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;



    public static RestauranteResponse toModel(Restaurante restaurante) {
        CozinhaResponse cozinhaResponse = new CozinhaResponse();
        cozinhaResponse.setId(restaurante.getCozinha().getId());
        cozinhaResponse.setNome(restaurante.getCozinha().getNome());

        RestauranteResponse restauranteResponse = new RestauranteResponse();
        restauranteResponse.setId(restaurante.getId());
        restauranteResponse.setNome(restaurante.getNome());
        restauranteResponse.setTaxaFrete(restaurante.getTaxaFrete());
        restauranteResponse.setStatus(restaurante.getAtivo());
        restauranteResponse.setCozinha(cozinhaResponse);
        return restauranteResponse;
    }

    public List<RestauranteResponse> toColletionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(RestauranteModelAssembler::toModel).collect(Collectors.toList());
    }
}
