package vn.ftel.perm.web.rest;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import vn.ftel.perm.services.PermService;

/**
 * Created by sinhns2.
 * Mail: sinhns2@fpt.com
 * Date: 02/13/25
 * Time: 03:05 PM
 * OpenFGARoute
 */
@Slf4j
@Path("/perm")
@SecurityRequirement(name = "UserToken")
@Tag(name = "OpenFGA API", description = "OpenFGA API Web applications")
public class OpenFGARoute {
    @Inject
    PermService permService;

    @GET
    @Path("/hello-world")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<Void>> hello() {
        return Uni.createFrom().item(RestResponse.ok());
    }

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<Void>> getPermissionByUserId(@PathParam("user_id") String userId) {
        return Uni.createFrom().item(RestResponse.ok());
    }

    @POST
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<Void>> setPermissionByUserId(
            @PathParam("user_id") String userId, @RequestBody String body) {
        return Uni.createFrom().item(RestResponse.ok());
    }

    @POST
    @Path("/{user_id}/check")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<RestResponse<Void>> checkUserOnPermission(
            @PathParam("user_id") String userId, @RequestBody String body) {
        return Uni.createFrom().item(RestResponse.ok());
    }
}
