package vn.ftel.perm.services;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient
public interface OpenFGAService {
    @POST
    @Path("/stores/{store_id}/check")
    Uni<Boolean> checkPermission(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("store_id") String storeId);

    @POST
    @Path("/stores/{store_id}/check")
    Uni<Boolean> queryPermission(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("store_id") String storeId);

    @POST
    @Path("/stores/{store_id}/write")
    Uni<Boolean> setPermission(
            @HeaderParam(HttpHeaders.AUTHORIZATION) String token, @PathParam("store_id") String storeId);
}
