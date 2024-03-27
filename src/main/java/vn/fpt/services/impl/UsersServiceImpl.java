package vn.fpt.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.auth.AssignAppRole;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.services.RolesService;
import vn.fpt.services.UsersService;
import vn.fpt.services.client.IamClientService;
import vn.fpt.web.dto.InviteUserInput;
import vn.fpt.web.dto.PaginateRequest;
import vn.fpt.web.errors.ErrorsEnum;
import vn.fpt.web.errors.exceptions.ServiceException;

/**
 * Auth Service Implement of {@link UsersService}.
 */

@Slf4j
@ApplicationScoped
public class UsersServiceImpl implements UsersService {

    @RestClient
    IamClientService iamClient;

    @Inject
    RolesService roleService;

    @Context
    ContainerRequestContext requestContext;

    /**
     * invite User access Service.
     *
     * @param dto the arg of request.
     */
    @Override
    public void invite(InviteUserInput dto) {

        IamUserInfo userInfo = check(dto);

        if(userInfo.isEnabled()) {

            AssignAppRole appRole = new AssignAppRole();
            appRole.setApp(dto.getApp());
            appRole.setRole("CHURN1");

            try {
                Response response = roleService.assignRole(dto.getToken(), userInfo.getId(), appRole);
                log.info(response.toString());
            } catch (Exception ex) {
                log.warn(ex.getMessage());
            }
        } else {
            ErrorsEnum error = ErrorsEnum.USERS_HAD_BEEN_INACTIVE;
            error.setMessage("i18n/error_messages", requestContext.getLanguage());

            throw new ServiceException(error);
        }
    }

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param paginateRequest the arg of request.
     * @return  IamPagination<IamUserInfo> dto of response.
     */
    @Override
    public IamPagination<IamUserInfo> getUsers(String token,
                                               PaginateRequest paginateRequest) {

        return iamClient.getUsers(token, paginateRequest);
    }

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param id   the arg of request.
     * @param app the arg of request.
     */
    @Override
    public IamUserInfo getUserById(String token,
                            String id,
                            String app) {

        return iamClient.getUserById(token, id, app);
    }

    /**
     * Check User was existed in Service.
     *
     * @param dto the arg of request.
     */
    @Override
    public IamUserInfo check(InviteUserInput dto) {

        return iamClient.check(dto.getToken(), dto.getApp(), dto.getEmail());
    }
}
