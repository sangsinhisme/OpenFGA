package vn.fpt.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import vn.fpt.models.EntityDevice;
import vn.fpt.repositories.EntityDeviceRepository;
import vn.fpt.services.EntityDevicesService;
import vn.fpt.services.StreamingService;
import vn.fpt.services.dto.CreateEntityDTO;
import vn.fpt.services.mappers.EntityDeviceMapper;
import vn.fpt.web.exceptions.ServiceException;

import java.util.UUID;

/**
 * Entity Devices Service Implement of {@link EntityDevicesService}.
 */

@Slf4j
@Transactional
@ApplicationScoped
public class EntityDevicesServiceImpl implements EntityDevicesService {

    @Inject
    EntityDeviceMapper entityDeviceMapper;

    @Inject
    EntityDeviceRepository entityDeviceRepository;

    /**
     * Create Entity Device.
     *
     * @param dto the data of object.
     */
    @Override
    @Transactional
    public void create(CreateEntityDTO dto) {

            EntityDevice entity = entityDeviceMapper.toEntity(dto);
            entityDeviceRepository.persistAndFlush(entity);
    }

    /**
     * Find one Entity Device by ID
     *
     * @param id of object.
     * @return EntityDevice.
     */
    @Override
    public EntityDevice findById(UUID id) throws ServiceException {
        return null;
    }
}
