package vn.fpt.services.client;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import vn.fpt.models.auth.AuthToken;
import vn.fpt.models.auth.DecryptAuth;
import vn.fpt.models.auth.DmcUserInfo;

@RequestScoped
@RegisterRestClient
public interface DmcClientService {

    /**
     * API decrypt Token.
     *
     * @param dto the arg of object decrypt.
     * @return AuthToken.
     */
    @POST
    @Path("/decryptToken")
    AuthToken decryptToken(@RequestBody DecryptAuth dto);

    /**
     * API get User Permission.
     *
     * @param appName the arg of object.
     * @param realmName the arg of object.
     * @param token the arg of object.
     *
     * @return DmcUserInfo.
     */
    @GET
    @Path("/getUserPermissionV2")
    DmcUserInfo getUserPermission(@QueryParam("appName") String appName,
                                  @QueryParam("realmName") String realmName,
                                  @QueryParam("token") String token);

    /**
     * API get User Permission async.
     *
     * @param appName the arg of object.
     * @param realmName the arg of object.
     * @param token the arg of object.
     *
     * @return DmcUserInfo.
     */
    @GET
    @Path("/getUserPermissionV2")
    Uni<DmcUserInfo> getUserPermissionAsync(@QueryParam("appName") String appName,
                                            @QueryParam("realmName") String realmName,
                                            @QueryParam("token") String token);
}
