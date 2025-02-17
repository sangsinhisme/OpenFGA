package vn.ftel.perm.services.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import vn.ftel.perm.services.PermService;

/**
 * OpenFga Service for managing {@link PermService}.
 */
@Slf4j
@ApplicationScoped
public class PermServiceImpl implements PermService {
    @Override
    public Uni<Boolean> getAllPermission(String token, String storeId) {
        return Uni.createFrom().item(true);
    }
}
