package vn.fpt.services.client;

import jakarta.ws.rs.*;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import vn.fpt.models.auth.AuthToken;
import vn.fpt.models.auth.DecryptAuth;

@RegisterRestClient
public interface DmcClientService {

    @POST
    @Path("/decryptToken")
    AuthToken decryptToken(@RequestBody DecryptAuth dto);

    @GET
    @Path("/getUserPermissionV2")
    AuthToken getUserPermission(@QueryParam("appName") String appName,
                                @QueryParam("realmName") String realmName,
                                @QueryParam("token") String token);
}
