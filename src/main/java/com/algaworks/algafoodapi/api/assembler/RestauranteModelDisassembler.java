package com.algaworks.algafoodapi.api.assembler;

import com.algaworks.algafoodapi.api.model.input.RestauranteInput;
import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import com.algaworks.algafoodapi.domain.model.entity.Restaurante;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RestauranteModelDisassembler {

    public Restaurante toEntity(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
        restaurante.setAtivo(restauranteInput.getStatus());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getIdCozinha());

        restaurante.setCozinha(cozinha);

        restaurante.setFormasPagamento(new ArrayList<>());

        return restaurante;
    }
}
