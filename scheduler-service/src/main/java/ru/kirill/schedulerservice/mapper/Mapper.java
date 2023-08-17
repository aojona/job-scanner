package ru.kirill.schedulerservice.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class Mapper<E, D> {

    private final ModelMapper modelMapper;
    private final Class<D> dtoClass;

    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }
}
