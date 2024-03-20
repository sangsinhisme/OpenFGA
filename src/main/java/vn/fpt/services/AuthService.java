package vn.fpt.services;

import jakarta.ws.rs.container.ContainerRequestContext;
import vn.fpt.models.auth.DmCUserInfo;

/**
 * Service Interface for managing {@link AuthService}.
 */
public interface AuthService {

    /**
     * Set User Info into form MinIO.
     *
     * @param requestContext the arg of object.
     * @param userInfo the arg of object.
     */
    void setSecurityContext(ContainerRequestContext requestContext, DmCUserInfo userInfo);

}
