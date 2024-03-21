package vn.fpt.services;

import jakarta.ws.rs.core.Response;
import vn.fpt.models.auth.AssignAppRole;
import vn.fpt.models.auth.BaseObject;
import vn.fpt.models.auth.RevokeAppRole;

/**
 * Service Interface for managing {@link RolesService}.
 */
public interface RolesService {

    /**
     * Assign role to User access Service.
     *
     * @param token  the arg of request.
     * @param userId the arg of request.
     * @param dto    the arg of request.
     */
    Response assignRole(String token,
                        String userId,
                        AssignAppRole dto);

    /**
     * Revoke role to User.
     *
     * @param token  the arg of request.
     * @param userId the arg of request.
     * @param dto    the arg of request.
     */
    Response revokeRole(String token,
                        String userId,
                        RevokeAppRole dto);

    /**
     * add User into list roles.
     *
     * @param token  the arg of request.
     * @param userId the arg of request.
     * @param dto    the arg of request.
     */
    Response rolesIn(String token,
                     String userId,
                     BaseObject dto);

    /**
     * remove User out of list roles.
     *
     * @param token  the arg of request.
     * @param userId the arg of request.
     * @param dto    the arg of request.
     */
    Response rolesOut(String token,
                      String userId,
                      BaseObject dto);
}
