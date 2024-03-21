package vn.fpt.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import vn.fpt.models.auth.AuthToken;
import vn.fpt.models.auth.DecryptAuth;
import vn.fpt.models.auth.DmCUserInfo;
import vn.fpt.services.AuthService;
import vn.fpt.services.client.DmcClientService;
import vn.fpt.web.errors.ErrorsEnum;
import vn.fpt.web.errors.exceptions.UnauthorizedException;

/**
 * Auth Service Implement of {@link AuthService}.
 */

@Slf4j
@ApplicationScoped
public class AuthServiceImpl implements AuthService {

    @RestClient
    DmcClientService dmcClient;

    /**
     * Set User Info into form MinIO.
     *
     * @param dto the arg of object.
     *
     * @return AuthToken dto of response
     */
    @Override
    public AuthToken decryptToken(DecryptAuth dto) {
        try {
            return dmcClient.decryptToken(dto);
        } catch (WebApplicationException ex) {
            log.error(ex.getMessage());

            throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);
        }
    }

    /**
     * Get User Permission.
     *
     * @param appName the arg of object.
     * @param realmName the arg of object.
     * @param token the arg of object.
     *
     * @return DmCUserInfo dto of response.
     */
    @Override
    public DmCUserInfo getUserPermission(String appName, String realmName, String token) {
        try {
            return dmcClient.getUserPermission(appName, realmName, token);
        } catch (WebApplicationException ex) {
           log.error(ex.getMessage());

           throw new UnauthorizedException(ErrorsEnum.AUTH_FAILED);
        }
    }
}
