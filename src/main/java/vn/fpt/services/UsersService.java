package vn.fpt.services;

import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;

/**
 * Service Interface for managing {@link UsersService}.
 */
public interface UsersService {

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param app   the arg of request.
     * @param email the arg of request.
     */
    void invite(String token, String app, String email);

    /**
     * invite User access Service.
     *
     * @param token the arg of request.
     * @param first   the arg of request.
     * @param max the arg of request.
     * @param app the arg of request.
     * @param order the arg of request.
     */
    IamPagination<IamUserInfo> getUsers(String token,
                                        Integer first,
                                        Integer max,
                                        String app,
                                        String order);

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
     * @param token the arg of request.
     * @param app   the arg of request.
     * @param search the arg of request.
     */
    IamUserInfo check(String token,
                      String app,
                      String search);
}
