package ru.kirill.restapi.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class Mapper<E, D1, D2> {

    private final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D1> requestDtoClass;
    private final Class<D2> responseDtoClass;

    @PostConstruct
    private void setup() {
        modelMapper
                .createTypeMap(entityClass, responseDtoClass)
                .setPostConverter(toDtoConverter());
        modelMapper
                .createTypeMap(requestDtoClass, entityClass)
                .setPostConverter(toEntityConverter());
    }

    public D2 toDto(E entity) {
        return modelMapper.map(entity, responseDtoClass);
    }

    public E toEntity(D1 dto) {
        return modelMapper.map(dto, entityClass);
    }

    public void updateEntity(D1 source, E entityToUpdate) {
        modelMapper.map(source, entityToUpdate);
    }

    private Converter<E,D2> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D2 destination = context.getDestination();
            mapToDto(source, destination);
            return destination;
        };
    }

    private Converter<D1,E> toEntityConverter() {
        return context -> {
            D1 source = context.getSource();
            E destination = context.getDestination();
            mapToEntity(source, destination);
            return destination;
        };
    }

    protected void mapToDto(E source, D2 destination) {}

    protected void mapToEntity(D1 source, E destination) {}
}
