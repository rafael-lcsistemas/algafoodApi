package com.algaworks.algafoodapi.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenericResponseAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public <E, D> D toModel(E entity, Class<D> destinationType) {
        return modelMapper.map(entity, destinationType);
    }

    public <E, D> List<D> toCollectionModel(List<E> entities, Class<D> destinationType) {
        return entities.stream()
                .map(entity -> toModel(entity, destinationType))
                .collect(Collectors.toList());
    }
}
