package ru.kirill.schedulerservice.mapper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public abstract class Mapper<E, D> {

    private final ModelMapper modelMapper;
    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    public D toDto(E entity) {
        return modelMapper.map(entity, dtoClass);
    }

    @PostConstruct
    private void setup() {
        modelMapper
                .createTypeMap(entityClass, dtoClass)
                .setPostConverter(toDtoConverter());
    }

    private Converter<E,D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapToDto(source, destination);
            return destination;
        };
    }

    protected void mapToDto(E source, D destination) {}
}
