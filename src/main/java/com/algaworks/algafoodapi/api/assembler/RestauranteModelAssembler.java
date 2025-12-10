package com.algaworks.algafoodapi.api.assembler;

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

    public RestauranteResponse toModel(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteResponse.class);
    }

    public List<RestauranteResponse> toColletionModel(List<Restaurante> restaurantes) {
        return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
    }
}
