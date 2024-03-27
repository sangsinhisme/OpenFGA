package vn.fpt.services;

import vn.fpt.models.auth.AuthToken;
import vn.fpt.models.auth.DecryptAuth;
import vn.fpt.models.auth.DmcUserInfo;

/**
 * Service Interface for managing {@link AuthService}.
 */
public interface AuthService {

    /**
     * Set User Info into form MinIO.
     *
     * @param dto the arg of object.
     *
     * @return AuthToken dto of response
     */
    AuthToken decryptToken(DecryptAuth dto);

    /**
     * Get User Permission.
     *
     * @param appName the arg of object.
     * @param realmName the arg of object.
     * @param token the arg of object.
     *
     * @return DmcUserInfo dto of response.
     */
    DmcUserInfo getUserPermission(String appName,
                                  String realmName,
                                  String token);
}
