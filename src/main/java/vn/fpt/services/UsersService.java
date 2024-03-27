package vn.fpt.services;

import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.web.dto.InviteUserInput;
import vn.fpt.web.dto.PaginateRequest;

/**
 * Service Interface for managing {@link UsersService}.
 */
public interface UsersService {

    /**
     * invite User access Service.
     *
     * @param dto the arg of request.
     */
    void invite(InviteUserInput dto);

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param paginateRequest the arg of request.
     * @return  IamPagination<IamUserInfo> dto of response.
     */
    IamPagination<IamUserInfo> getUsers(String token,
                                        PaginateRequest paginateRequest);

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param id   the arg of request.
     * @param app the arg of request.
     */
    IamUserInfo getUserById(String token,
                            String id,
                            String app);

    /**
     * Check User was existed in Service.
     *
     * @param dto the arg of request.
     * @return IamUserInfo
     */
    IamUserInfo check(InviteUserInput dto);
}
