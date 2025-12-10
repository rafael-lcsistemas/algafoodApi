package com.algaworks.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericInputAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public <T, D> D toEntity(T input, Class<D> destinationType) {
        return modelMapper.map(input, destinationType);
    }
}
