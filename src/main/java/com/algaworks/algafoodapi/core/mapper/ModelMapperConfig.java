package com.algaworks.algafoodapi.core.mapper;

import com.algaworks.algafoodapi.api.model.response.EnderecoResponse;
import com.algaworks.algafoodapi.domain.model.entity.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        // EnderecoResponse -> Endereco
        var enderecoResponseTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoResponse.class);

        enderecoResponseTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value));

        enderecoResponseTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getUf(),
                (dest, value) -> dest.getCidade().setUf(value));

        return modelMapper;
    }
}
