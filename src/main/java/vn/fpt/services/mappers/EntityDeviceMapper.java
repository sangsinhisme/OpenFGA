package vn.fpt.services.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.fpt.models.EntityDevice;
import vn.fpt.services.dto.CreateEntityDTO;

/**
 * Mapper for the entity {@link EntityDevice} and its DTO {@link CreateEntityDTO}.
 */

@Mapper(
        componentModel = "jakarta",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface EntityDeviceMapper extends EntityMapper<CreateEntityDTO, EntityDevice> {

    @BeanMapping(ignoreByDefault = true)
    CreateEntityDTO toDto(EntityDevice entity);

    @Mapping(target = "createdBy" , ignore = true)
    @Mapping(target = "createdDate" , ignore = true)
    @Mapping(target = "lastModifiedBy" , ignore = true)
    @Mapping(target = "lastModifiedDate" , ignore = true)
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "status" , ignore = true)
    EntityDevice toEntity(CreateEntityDTO dto);

}