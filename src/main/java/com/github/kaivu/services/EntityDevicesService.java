package com.github.kaivu.services;

import com.github.kaivu.models.EntityDevice;
import com.github.kaivu.services.dto.CreateEntityDTO;
import com.github.kaivu.web.vm.EntityDeviceVM;
import io.smallrye.mutiny.Uni;

import java.util.UUID;

/**
 * Service Interface for managing {@link EntityDevicesService}.
 */
public interface EntityDevicesService
        extends BaseReadService<EntityDevice, UUID>, BaseWriteService<EntityDevice, UUID> {

    /**
     * Create Entity Device.
     *
     * @param dto the data of object.
     */
    Uni<EntityDeviceVM> create(CreateEntityDTO dto);
}
