package com.github.kaivu.repositories;

import com.github.kaivu.models.EntityDevice;
import com.github.kaivu.repositories.base.BaseReadRepository;
import com.github.kaivu.repositories.base.BaseWriteRepository;

import java.util.UUID;

public interface IEntityDeviceRepository
        extends BaseWriteRepository<EntityDevice>, BaseReadRepository<EntityDevice, UUID> {}
