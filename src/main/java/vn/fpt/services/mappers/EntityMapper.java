package vn.fpt.services.mappers;

import java.util.List;

import jakarta.enterprise.inject.Decorated;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);

}