package vn.fpt.services.client;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestForm;
import vn.fpt.models.users.IamPagination;
import vn.fpt.models.users.IamUserInfo;
import vn.fpt.models.users.UpdateUserDetails;

@RegisterRestClient
public interface IamClientService {

    @POST
    @Path("/cads/users/{id}")
    IamUserInfo createUser(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                               @PathParam("id") String id,
                               @QueryParam("app") String app,
                               @RequestBody UpdateUserDetails dto);

    @PUT
    @Path("/cads/users/{id}")
    IamUserInfo updateUserByID(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                               @PathParam("id") String id,
                               @QueryParam("app") String app,
                               @RequestBody UpdateUserDetails dto);

    @GET
    @Path("/cads/users")
    IamPagination<IamUserInfo> getUsers(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                        @QueryParam("first") Integer first,
                                        @QueryParam("max") Integer max,
                                        @QueryParam("app") String app,
                                        @QueryParam("order") String order);

    @GET
    @Path("/cads/users/{id}")
    IamUserInfo getUserById(@HeaderParam(HttpHeaders.AUTHORIZATION) String token,
                                           @PathParam("id") String id,
                                           @QueryParam("app") String app);

}
