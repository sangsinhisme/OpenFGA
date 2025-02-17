package vn.ftel.perm.services;

import io.smallrye.mutiny.Uni;

/**
 * Service Interface for managing {@link PermService}.
 */
public interface PermService {
    Uni<Boolean> getAllPermission(String token, String storeId);
}
