package vn.fpt.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.auth.AssignAppRole;
import vn.fpt.models.auth.RevokeAppRole;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.services.RolesService;
import vn.fpt.services.UsersService;
import vn.fpt.services.client.IamClientService;

/**
 * Auth Service Implement of {@link RolesService}.
 */

@Slf4j
@ApplicationScoped
public class RolesServiceImpl implements RolesService {

    @RestClient
    IamClientService iamClient;

    /**
     * Assign role to User access Service.
     *
     * @param token the arg of request.
     * @param userId the arg of request.
     * @param dto the arg of request.
     */
    @Override
    public Response assignRole(String token, String userId, AssignAppRole dto) {
        return iamClient.assignRole(token, userId, dto);
    }

    /**
     * Revoke role to User.
     *
     * @param token the arg of request.
     * @param userId the arg of request.
     * @param dto the arg of request.
     */
    @Override
    public Response revokeRole(String token, String userId, RevokeAppRole dto) {
        return iamClient.revokeRole(token, userId, dto);
    }
}
