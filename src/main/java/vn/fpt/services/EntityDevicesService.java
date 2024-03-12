package vn.fpt.services;

import vn.fpt.models.EntityDevice;
import vn.fpt.services.dto.CreateEntityDTO;
import vn.fpt.web.exceptions.ServiceException;

import java.util.UUID;

/**
 * Service Interface for managing {@link EntityDevicesService}.
 */
public interface EntityDevicesService {

    /**
     * Create Entity Device.
     *
     * @param dto the data of object.
     */
    void create(CreateEntityDTO dto);

    /**
     * Find one Entity Device by ID
     *
     * @param id of object.
     * @return EntityDevice.
     */
    EntityDevice findById(UUID id) throws ServiceException;
}
